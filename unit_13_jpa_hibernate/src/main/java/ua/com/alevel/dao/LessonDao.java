package ua.com.alevel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.alevel.model.entity.Lesson;
import ua.com.alevel.model.entity.Teacher;
import ua.com.alevel.exception.ResourceNotFoundException;
import ua.com.alevel.model.dto.LessonDto;

import javax.persistence.Query;

import static ua.com.alevel.controller.MainController.logger;

public class LessonDao {

    public LessonDto findNearestLesson(int id, SessionFactory sessionFactory) throws ResourceNotFoundException {
        Lesson lesson = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            if (teacher == null) {
                session.getTransaction().rollback();
                throw new ResourceNotFoundException(id);
            }
            Query query = session.createQuery("select l from Student st inner join Group gr on gr.id = st.group.id " +
                    "and st.id = :idStudent join Lesson l on l.course.id = gr.course.id " +
                    "and l.dateAndTime > CURRENT_DATE order by l.dateAndTime");
            query.setParameter("idStudent", id);
            session.getTransaction().commit();
            if (query.getResultList().isEmpty()) {
                session.getTransaction().rollback();
                throw new ResourceNotFoundException(id);
            }
            lesson = (Lesson) query.getResultList().get(0);
        } catch (HibernateException e) {
            logger.error("Error in transaction!");
        }
        return entityToDtoForLesson(lesson);
    }

    private static LessonDto entityToDtoForLesson(Lesson entity) {
        return new LessonDto(
                entity.getId(),
                entity.getName(),
                entity.getDateAndTime()
        );
    }
}

