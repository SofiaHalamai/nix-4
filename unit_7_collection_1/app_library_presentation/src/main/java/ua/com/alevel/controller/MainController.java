package ua.com.alevel.controller;

import static ua.com.alevel.Main.scan;

public class MainController {

    public void run(){
        while(true){
            System.out.println("\tThis is a presentation of the library - Ordered List, some of its methods, you can try on the example of objects:" + "\n" + "1. Integers" + "\n" +
                    "2. Strings" + "\n" +
                    "3. Book (by default, Comparable interface is already implemented here (comparison - if the names are the same, we compare the age)" + "\n\t\t" +
                    "also overridden equals () and hashCode ()" + "\n" +
                    "4. Exit" + "\n" + "Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    IntegerController integerController = new IntegerController();
                    integerController.runInt();
                    break;
                case "2":
                    StringController stringController = new StringController();
                    stringController.runStr();
                    break;
                case "3":
                    BookController bookController = new BookController();
                    bookController.runBook();
                    break;
                case "4":
                    System.exit(1);
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }

    public static void menuForList(){
        System.out.println("1. Add object" + "\n" +
                "2. Remove object" + "\n" +
                "3. Get object by index" + "\n" +
                "4. Get size list" + "\n" +
                "5. Check for the contains of object" + "\n" +
                "6. Clear" + "\n" +
                "7. Check for empty" + "\n" +
                "8. Print list" + "\n" +
                "9. Return to the main menu" + "\n" + "Select one of the points: ");
    }
}
