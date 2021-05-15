package ua.com.alevel.controller;

import ua.com.alevel.firstTask.CheckingDatesForFormat;
import ua.com.alevel.secondTask.FindingFirstUniqueName;
import ua.com.alevel.thirdTask.FindingPathBetweenCities;
import ua.com.alevel.util.WriteAndReadFromFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class MainController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String DATE_INPUT = "./src/main/resources/files/listOfDates/inputDates.txt";
    public static final String DATE_OUTPUT = "./src/main/resources/files/listOfDates/outputDates.txt";
    public static final String NAME_INPUT = "./src/main/resources/files/listOfNames/inputNames.txt";
    public static final String NAME_OUTPUT = "./src/main/resources/files/listOfNames/outputNames.txt";
    public static final String CITY_INPUT = "./src/main/resources/files/listOfCities/inputCities.txt";
    public static final String CITY_OUTPUT = "./src/main/resources/files/listOfCities/outputCities.txt";

    public void run() throws IOException {
        while (true) {
            System.out.println("=============================");
            System.out.println("||      MENU MODULE 2      ||");
            System.out.println("||           ||            ||");
            System.out.println("||BE SURE TO READ README.MD||");
            System.out.println("=============================");
            System.out.println("|_ _ _ _ _ 1. TASK 1 _ _ _ _|");
            System.out.println("|_ _ _ _ _ 2. TASK 2 _ _ _ _|");
            System.out.println("|_ _ _ _ _ 3. TASK 3 _ _ _ _|");
            System.out.println("|_ _ _ _ _ 4. EXIT _ _ _ _ _|");
            System.out.println("=============================\n");
            System.out.print(ANSI_PURPLE + "SELECT ONE OF THE POINTS:  " + ANSI_RESET);
            String position = reader.readLine();
            switch (position) {
                case "1":
                    runFirstTask();
                    break;
                case "2":
                    runSecondTask();
                    break;
                case "3":
                    runThirdTask();
                    break;
                case "4":
                    System.exit(1);
                default:
                    System.out.println(ANSI_GREEN + "SELECT ONE OF SUGGESTED POINTS!" + ANSI_RESET);
                    break;
            }
        }
    }

    private static void runFirstTask(){
        System.out.println(ANSI_BLUE + "READING DATES FROM FILE ... RESULT:  " + ANSI_RESET);
        List<String> dates = WriteAndReadFromFiles.readFromFile(DATE_INPUT);
        System.out.println(dates);
        WriteAndReadFromFiles.writeToFile(CheckingDatesForFormat.findCorrectDate(dates), DATE_OUTPUT);
        System.out.println(ANSI_BLUE + "THE RESULT WAS SUCCESSFULLY WRITTEN TO THE FILE!  " + ANSI_RESET);
        System.out.print(ANSI_BLUE + "CHECKING... DATES READ FROM THE RESULTING FILE:  " + ANSI_RESET);
        System.out.println(WriteAndReadFromFiles.readFromFile(DATE_OUTPUT));
    }

    private static void runSecondTask(){
        System.out.println(ANSI_BLUE + "READING NAMES FROM FILE ... RESULT:  " + ANSI_RESET);
        List<String> names = WriteAndReadFromFiles.readFromFile(NAME_INPUT);
        System.out.println(names);
        WriteAndReadFromFiles.writeToFile(Collections.singletonList(FindingFirstUniqueName.findUniqueName(names)), NAME_OUTPUT);
        System.out.println(ANSI_BLUE + "THE RESULT WAS SUCCESSFULLY WRITTEN TO THE FILE!  " + ANSI_RESET);
        System.out.print(ANSI_BLUE + "CHECKING... UNIQUE NAME READ FROM THE RESULTING FILE:  " + ANSI_RESET);
        System.out.println(WriteAndReadFromFiles.readFromFile(NAME_OUTPUT));
    }

    private static void runThirdTask() throws IOException {
        FindingPathBetweenCities.workWithFileCities(CITY_INPUT, CITY_OUTPUT);
        System.out.println(ANSI_BLUE + "THE RESULT WAS SUCCESSFULLY WRITTEN TO THE FILE!  " + ANSI_RESET);
        System.out.print(ANSI_BLUE + "CHECKING... READ ALL SHORTEST WAYS FROM THE RESULTING FILE:  " + ANSI_RESET);
        System.out.println(WriteAndReadFromFiles.readFromFile(CITY_OUTPUT));
    }
}
