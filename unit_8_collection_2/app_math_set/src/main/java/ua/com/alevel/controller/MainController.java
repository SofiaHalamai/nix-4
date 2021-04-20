package ua.com.alevel.controller;

import static ua.com.alevel.Main.scan;

public class MainController {

    public void run() {
        while (true) {
            System.out.println("\tThis is a presentation of the library - MathSet (elements of this set only heirs Number), some of its methods, "
                    + "\n\t" + "you can try on the example and please select element's type:" + "\n" +
                    "1. Integer" + "\n" +
                    "2. Double" + "\n" +
                    "3. Exit" + "\n" + "Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    IntegerController integerController = new IntegerController();
                    integerController.runInt();
                    break;
                case "2":
                    DoubleController doubleController = new DoubleController();
                    doubleController.runDouble();
                    break;
                case "3":
                    System.exit(1);
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }

    public static void menuForSet() {
        System.out.println("1. Add value" + "\n" +
                "2. Join" + "\n" +
                "3. Ascending Sort" + "\n" +
                "4. Descending Sort" + "\n" +
                "5. Get element by index" + "\n" +
                "6. Get max value from set" + "\n" +
                "7. Get min value from set" + "\n" +
                "8. Get average value from set" + "\n" +
                "9. Get median" + "\n" +
                "10. Squash" + "\n" +
                "11. Clear" + "\n" +
                "12. Print set" + "\n" +
                "13. Return to the main menu" + "\n" + "Select one of the points: ");
    }
}
