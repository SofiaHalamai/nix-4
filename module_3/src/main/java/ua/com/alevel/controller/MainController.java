package ua.com.alevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static ua.com.alevel.controller.HibernateController.runHibernatePartApp;
import static ua.com.alevel.controller.JDBCController.runJDBCPartApp;

public class MainController {

    public static final Logger logger = LoggerFactory.getLogger(MainController.class);
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws IOException {
        while (true) {
            logger.info("====================================================================\n");
            logger.info("|_ _ _ _ _ 1. Adding a new operation by an existing user_ _ _ _ _ _|\n");
            logger.info("|_ _ _ _ _ 2. Export of account statements in csv format_ _ _ _ _ _|\n");
            logger.info("|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _3. EXIT_ _ _ _ _ _ _ _ _ _ _  _ _|\n");
            logger.info("====================================================================\n");
            logger.debug("Select one of the points: ");
            String position = reader.readLine();
            switch (position) {
                case "1":
                    runHibernatePartApp(reader);
                    break;
                case "2":
                    runJDBCPartApp(reader);
                    break;
                case "3":
                    System.exit(1);
                default:
                    logger.warn("Select one of suggested points!");
                    break;
            }
        }
    }

    public static boolean checkCorrectAccountID (List<Integer> numbersAccount, int choice){
        for (Integer integer : numbersAccount) {
            if (integer == choice) return true;
        }
        logger.error("Incorrect id! Try again!");
        return false;
    }
}
