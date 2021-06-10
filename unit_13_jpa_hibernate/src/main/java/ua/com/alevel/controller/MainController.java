package ua.com.alevel.controller;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.exception.ResourceNotFoundException;
import ua.com.alevel.model.dto.GroupDto;
import ua.com.alevel.model.dto.LessonDto;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.LessonService;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainController {

    public static Logger logger = LoggerFactory.getLogger(MainController.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public void run() throws IOException{
        while (true) {
            logger.info("=====================================================================\n");
            logger.info("|_ _ _ _ _ 1. Search for the nearest lesson by student ID_ _ _ _ _ _|\n");
            logger.info("|_ _ _ _ _ 2. Search the most successful group by teacher ID _ _ _ _|\n");
            logger.info("|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _3. EXIT_ _ _ _ _ _ _ _ _ _ _ _ _ _|\n");
            logger.info("=====================================================================\n");
            logger.debug("Select one of the points: ");
            String position = reader.readLine();
            switch (position) {
                case "1":
                    runFirstTask();
                    break;
                case "2":
                    runSecondTask();
                    break;
                case "3":
                    System.exit(1);
                default:
                    logger.warn("Select one of suggested points!");
                    break;
            }
        }
    }

    private static void runFirstTask() throws IOException{
        LessonService lessonService = new LessonService();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        logger.debug("Enter student ID: ");
        int idStudent = Integer.parseInt(reader.readLine());
        logger.debug("We send requests to the database... " +
                "we start searching for the nearest lesson, ID student " + idStudent);
        LessonDto l;
        try {
            l = lessonService.findNearestLesson(idStudent, sessionFactory);
        logger.debug(String.format("\n\nNext lesson ->  %s \nDate and time: %s\n\n",
                l.getName(), format.format(l.getDateAndTime())));
        } catch (ResourceNotFoundException e) {
            logger.error("\n\nStudent with id = " + idStudent + " was not found!\n\n");
        }
    }

    private static void runSecondTask() throws IOException {
        GroupService groupService = new GroupService();

        logger.debug("Enter teacher ID: ");
        int idTeacher = Integer.parseInt(reader.readLine());

        GroupDto group;
        try {
            group = groupService.findByTeacherId(idTeacher, sessionFactory);
            logger.debug(String.format("\n\nThe best of the best group -> Name: %s\n Median final exam score: %s\n\n",
                    group.getName(), group.getAvgMark()));
        } catch (ResourceNotFoundException e) {
            logger.error("\n\nTeacher with id = " + idTeacher+ " was not found! Or this teacher has no groups\n\n");
        }
    }
}
