package ua.com.alevel;

import ua.com.alevel.conroller.CalendarController;

import java.io.IOException;
import java.util.Scanner;

public class CalendarMain {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        CalendarController calendarController = new CalendarController();
        try {
            calendarController.run();
        } catch (IOException e) {
            System.out.println("Incorrect input");
        }
    }
}
