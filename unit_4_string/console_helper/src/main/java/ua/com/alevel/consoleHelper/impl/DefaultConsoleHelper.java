package ua.com.alevel.consoleHelper.impl;

import ua.com.alevel.consoleHelper.ConsoleHelper;

import java.util.Scanner;

public class DefaultConsoleHelper implements ConsoleHelper {
    public static Scanner scan = new Scanner(System.in);

    @Override
    public String dataInput(String input) {
        String inputStr;
        System.out.println(input);
        inputStr = scan.nextLine();
        return inputStr;
    }

    @Override
    public void dataOutput(String output) {
        System.out.println(output);
    }
}
