package ua.com.alevel.service;

import org.hibernate.SessionFactory;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.exception.ResourceNotFoundException;
import ua.com.alevel.model.dto.GroupDto;

public class GroupService {
    GroupDao groupDao = new GroupDao();

    public GroupDto findByTeacherId(int id, SessionFactory sessionFactory) throws ResourceNotFoundException {
        return groupDao.findByTeacherId(id, sessionFactory);
    }
}
