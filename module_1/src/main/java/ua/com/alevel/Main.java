package ua.com.alevel;

import ua.com.alevel.conroller.MainController;

import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.run();
    }
}
