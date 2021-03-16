package ua.com.alevel.help_service.impl;

import ua.com.alevel.help_service.HelperService;

import java.util.Scanner;

public class DefaultHelperService implements HelperService {
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
