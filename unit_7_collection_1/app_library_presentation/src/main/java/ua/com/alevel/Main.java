package ua.com.alevel;

import ua.com.alevel.controller.MainController;

import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.run();
    }
}
