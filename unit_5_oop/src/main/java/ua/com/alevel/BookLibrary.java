package ua.com.alevel;

import ua.com.alevel.controller.CrudController;

import java.util.Scanner;


public class BookLibrary {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        CrudController crudController = new CrudController();
        crudController.run();
    }
}
