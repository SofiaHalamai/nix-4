package ua.com.alevel.secondLevel;

import ua.com.alevel.secondLevel.stringValidation.IntroducingStringToBeChecked;

import static ua.com.alevel.Main.scan;

public class SecondLevelStarting {

    public void secondLevelRun(){
        boolean tmp = true;
        while (tmp) {
            System.out.print("\tSECOND LEVEL" + "\n" + "1. Checking the input string for validity" + "\n" +
                    "2. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    IntroducingStringToBeChecked stringToBeChecked = new IntroducingStringToBeChecked();
                    stringToBeChecked.stringInputAndValidation();
                    break;
                case "2":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }
}
