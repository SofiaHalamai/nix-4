package ua.com.alevel;


import ua.com.alevel.controller.MainController;
import java.io.*;

import static ua.com.alevel.controller.MainController.*;

public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        try {
            mainController.run();
        } catch (IOException e) {
            System.out.println(ANSI_GREEN + "INVALID FILE PATH" + ANSI_RESET);
        }
    }
}
