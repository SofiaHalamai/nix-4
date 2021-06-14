package ua.com.alevel;

import ua.com.alevel.controller.MainController;
import java.io.IOException;

import static ua.com.alevel.controller.MainController.logger;

public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        try {
            mainController.run();
        } catch (IOException e) {
            logger.error("Error using BufferedReader");
        }
    }
}
