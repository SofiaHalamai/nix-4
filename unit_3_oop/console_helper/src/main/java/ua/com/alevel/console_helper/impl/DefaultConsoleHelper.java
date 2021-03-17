package ua.com.alevel.console_helper.impl;

import ua.com.alevel.console_helper.ConsoleHelper;

import java.util.Scanner;

public class DefaultConsoleHelper implements ConsoleHelper {
    public static Scanner scan = new Scanner(System.in);

    @Override
    public String dataInput(String input) {

        String inputStr;
        System.out.print(input + "\n" + "Enter a value: ");
        inputStr = scan.nextLine();
        return inputStr;
    }

    @Override
    public void dataOutput(String output) {
        System.out.println("Result = " + output);
    }
}
