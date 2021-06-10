package ua.com.alevel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.alevel.model.entity.Group;
import ua.com.alevel.exception.ResourceNotFoundException;
import ua.com.alevel.model.dto.GroupDto;

import javax.persistence.Query;
import java.util.List;

import static ua.com.alevel.controller.MainController.logger;

public class GroupDao {

    public GroupDto findByTeacherId(int id, SessionFactory sessionFactory) throws ResourceNotFoundException {
        int bestMark = 0;
        Group bestOfTheBestGroup = new Group();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("select g from Teacher t join Course cr on t.course.id = cr.id " +
                    "join Group g on cr.id = g.course.id where t.id = :idTeacher");
            query.setParameter("idTeacher", id);
            session.getTransaction().commit();
            List<Group> groups = query.getResultList();
            if (groups.isEmpty()) {
                session.getTransaction().rollback();
                throw new ResourceNotFoundException();
            }
            for (Group group : groups) {
                query = session.createSQLQuery("with t as " +
                        "(select *, row_number() over (order by m.point) as rn, " +
                        "count(*) over() as rc " +
                        "from marks m where m.group_id = :id) " +
                        "select t.point from t where rn in ((rc+1)/2, (rc+2)/2)")
                        .setParameter("id", group.getId());
                int mark = (int) query.getResultList().get(0);
                if (bestMark == 0) {
                    bestOfTheBestGroup = group;
                    bestMark = mark;
                } else {
                    if (bestMark < mark) {
                        bestOfTheBestGroup = group;
                        bestMark = mark;
                    }
                }
            }
        } catch (HibernateException e) {
            logger.error("Error in transaction!");
        }
        return entityToDtoForGroup(bestOfTheBestGroup, bestMark);
    }

    private static GroupDto entityToDtoForGroup(Group entity, int avgMark) {
        return new GroupDto(
                entity.getId(),
                entity.getName(),
                avgMark
        );
    }
}
