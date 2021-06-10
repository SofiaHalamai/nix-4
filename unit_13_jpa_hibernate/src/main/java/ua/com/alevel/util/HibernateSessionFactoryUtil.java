package ua.com.alevel.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.model.entity.*;

import static ua.com.alevel.controller.MainController.logger;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Lesson.class);
                configuration.addAnnotatedClass(Mark.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(Topic.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.error("Error in creating hibernate session");
            }
        }
        return sessionFactory;
    }

}
