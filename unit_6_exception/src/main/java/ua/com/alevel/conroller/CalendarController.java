package ua.com.alevel.conroller;

import ua.com.alevel.entity.Date;
import ua.com.alevel.exception.FormatDateException;
import ua.com.alevel.service.Calendar;
import ua.com.alevel.service.impl.CalendarImpl;

import java.io.IOException;
import java.util.List;

import static ua.com.alevel.CalendarMain.scan;

public class CalendarController {

    DateFormatController dateFormatController = new DateFormatController();
    final Calendar calendar = new CalendarImpl();

    public void run() throws IOException {
        Date date;
        while (true) {
            System.out.println("\tCALENDAR" + "\n" + "1. Found difference between dates" + "\n" +
                    "2. Add time to date" + "\n" +
                    "3. Subtract time from date" + "\n" +
                    "4. Compare list of dates in descending order" + "\n" +
                    "5. Compare list of dates in ascending order" + "\n" +
                    "6. Exit" + "\n" + "Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    Date date1;
                    Date date2;
                    while (true) {
                        while (true) {
                            try {
                                date1 = dateFormatController.runFormat();
                                date2 = dateFormatController.runFormat();
                                break;
                            } catch (FormatDateException e) {
                                System.out.println("Incorrect input be format. Try again");
                            }
                        }
                        try {
                            calendar.differenceBetweenDate(date1, date2);
                            break;
                        } catch (FormatDateException e) {
                            System.out.println("Incorrect input one date is greater than the other and negative values are obtained. Try again");
                        }
                    }
                    break;
                case "2":
                    while (true) {
                        try {
                            date = dateFormatController.runFormat();
                            date = calendar.addTimeToDate(choiceUnitsOfTimeForAdd(date));
                            System.out.println(dateFormatController.printFormat(date));
                            break;
                        } catch (FormatDateException e) {
                            System.out.println("Incorrect input be format. Try again");
                        }
                    }
                    break;
                case "3":
                    while (true) {
                        while (true) {
                            try {
                                date = dateFormatController.runFormat();
                                break;
                            } catch (FormatDateException e) {
                                System.out.println("Incorrect input be format. Try again");
                            }
                        }
                        try {
                            date = choiceUnitsOfTimeForSubtract(date);
                            if (date == null) {
                                System.out.println("Incorrect input. Negative values are obtained. Try again");
                                break;
                            }
                            System.out.println(dateFormatController.printFormat(date));
                            break;
                        } catch (FormatDateException e) {
                            System.out.println("Incorrect input. Negative values are obtained. Try again");
                        }
                    }
                    break;
                case "4":
                    List<Date> list = sortOfDatesByDescending();
                    System.out.println("Enter format for every value, they will be in the sort order");
                    for (Date value : list) {
                        try {
                            System.out.println(dateFormatController.printFormat(value));
                        } catch (FormatDateException e) {
                            System.out.println("Error");
                        }
                    }
                    break;
                case "5":
                    List<Date> dateList = sortOfDatesByAscending();
                    System.out.println("Enter format for every value, they will be in the sort order");
                    for (Date value : dateList) {
                        try {
                            System.out.println(dateFormatController.printFormat(value));
                        } catch (FormatDateException e) {
                            System.out.println("Error");
                        }                    }
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }


    public Date choiceUnitsOfTimeForAdd(Date date) {
        Date newDate = null;
        System.out.println("\tCHOICE UNITS OF TIME" + "\n" + "1. Add milliseconds" + "\n" +
                "2. Add seconds" + "\n" +
                "3. Add minute" + "\n" +
                "4. Add hour" + "\n" +
                "5. Add years" + "\n" +
                "6. Add centuries" + "\n" + "Select one of the points: ");
        String position = scan.next();
        switch (position) {
            case "1":
                System.out.print("Enter count which you want add: ");
                newDate = calendar.addMsToDate(date, scan.nextInt());
                break;
            case "2":
                System.out.print("Enter count which you want add: ");
                newDate = calendar.addSecondsToDate(date, scan.nextInt());
                break;
            case "3":
                System.out.print("Enter count which you want add: ");
                newDate = calendar.addMinutesToDate(date, scan.nextInt());
                break;
            case "4":
                System.out.print("Enter count which you want add: ");
                newDate = calendar.addHoursToDate(date, scan.nextInt());
                break;
            case "5":
                System.out.print("Enter count which you want add: ");
                newDate = calendar.addYearsToDate(date, scan.nextInt());
                break;
            case "6":
                System.out.print("Enter count which you want add: ");
                newDate = calendar.addCenturiesToDate(date, scan.nextInt());
                break;
            default:
                System.out.println("Select one of the suggested points!");
                break;
        }
        return newDate;
    }

    public Date choiceUnitsOfTimeForSubtract(Date date) throws FormatDateException {
        Date newDate = null;
        System.out.println("\tCHOICE UNITS OF TIME" + "\n" + "1. Subtract milliseconds" + "\n" +
                "2. Subtract seconds" + "\n" +
                "3. Subtract minute" + "\n" +
                "4. Subtract hour" + "\n" +
                "5. Subtract years" + "\n" +
                "6. Subtract centuries" + "\n" + "Select one of the points: ");
        String position = scan.next();
        switch (position) {
            case "1":
                System.out.print("Enter count which you want subtract: ");
                newDate = calendar.subtractTimeToDate(date, scan.nextInt());
                break;
            case "2":
                System.out.print("Enter count which you want subtract: ");
                newDate = calendar.subtractTimeToDate(date, scan.nextInt() * 1000L);
                break;
            case "3":
                System.out.print("Enter count which you want subtract: ");
                newDate = calendar.subtractTimeToDate(date, scan.nextInt() * 60000L);
                break;
            case "4":
                System.out.print("Enter count which you want subtract: ");
                newDate = calendar.subtractTimeToDate(date, scan.nextInt() * 3600000L);
                break;
            case "5":
                System.out.print("Enter count which you want subtract: ");
                newDate = calendar.subtractYearsToDate(date, scan.nextInt());
                break;
            case "6":
                System.out.print("Enter count which you want subtract: ");
                newDate = calendar.subtractYearsToDate(date, scan.nextInt() * 100L);
                break;
            default:
                System.out.println("Select one of the suggested points!");
                break;
        }
        return newDate;
    }


    public List<Date> sortOfDatesByAscending() throws IOException {
        System.out.println("Enter count of dates which you want sorted: ");
        int countOfDates = scan.nextInt();
        Date[] dates = new Date[countOfDates];
        for (int i = 0; i < countOfDates; i++) {
            while (true) {
                try {
                    dates[i] = dateFormatController.runFormat();
                    break;
                } catch (FormatDateException e) {
                    System.out.println("Incorrect input be format. Try again");
                }
            }
        }
        return calendar.sortAscending(dates);
    }

    public List<Date> sortOfDatesByDescending() throws IOException {
        System.out.println("Enter count of dates which you want sorted: ");
        int countOfDates = scan.nextInt();
        Date[] dates = new Date[countOfDates];
        for (int i = 0; i < countOfDates; i++) {
            while (true) {
                try {
                    dates[i] = dateFormatController.runFormat();
                    break;
                } catch (FormatDateException e) {
                    System.out.println("Incorrect input be format. Try again");
                }
            }
        }
        return calendar.sortDescending(dates);
    }
}
