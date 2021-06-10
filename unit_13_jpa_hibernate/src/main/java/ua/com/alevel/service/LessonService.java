package ua.com.alevel.service;

import org.hibernate.SessionFactory;
import ua.com.alevel.dao.LessonDao;
import ua.com.alevel.exception.ResourceNotFoundException;
import ua.com.alevel.model.dto.LessonDto;

public class LessonService {

    LessonDao lessonDao = new LessonDao();

    public LessonDto findNearestLesson(int id, SessionFactory sessionFactory) throws ResourceNotFoundException {
        return lessonDao.findNearestLesson(id, sessionFactory);
    }
}
