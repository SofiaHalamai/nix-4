package ua.com.alevel.util;

import ua.com.alevel.entity.Date;
import ua.com.alevel.exception.FormatDateException;

public class CheckCorrectFormatOfDate {

    public Date createDateByFormat(String format, String dateInFormat) throws FormatDateException {
        Date date = new Date();
        String splitFormat[] = format.split("/|\\s|:|-");
        String splitDate[] = dateInFormat.split("/|\\s|:|-");
        if ((format.contains("/") && dateInFormat.contains("/")) || (format.contains(" ") && dateInFormat.contains(" ")) || (format.contains(":") && dateInFormat.contains(":")) || (format.contains("") && dateInFormat.contains(""))) {
        } else {
            throw new FormatDateException("Еhe date does not match the entered format!");
        }
        try {
            for (int i = 0; i < splitFormat.length; i++) {
                switch (splitFormat[i]) {
                    case "d":
                    case "dd":
                        if (Integer.parseInt(splitDate[i]) < 0 || Integer.parseInt(splitDate[i]) > 31)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("")) {
                            date.setDay(1);
                        } else {
                            date.setDay(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "m":
                    case "mm":
                        if (Integer.parseInt(splitDate[i]) < 0 || Integer.parseInt(splitDate[i]) > 12)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("")) {
                            date.setMonth(1);
                        } else {
                            date.setMonth(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "mmm":
                        boolean correctMonth = false;
                        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        for (int j = 0; j < months.length; j++) {
                            if (splitDate[i].equals(months[j])) {
                                date.setMonth(j + 1);
                                correctMonth = true;
                                break;
                            }
                        }
                        if (!correctMonth) {
                            throw new FormatDateException("Incorrect input by format");
                        }
                        break;
                    case "yy":
                        if (getCountsOfDigits(Integer.parseInt(splitDate[i])) != 2 || Integer.parseInt(splitDate[i]) < 0)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("")) {
                            date.setYear(2021);
                        } else if (splitDate[i].length() == 2) {
                            date.setYear(1900 + Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "yyyy":
                        if (Integer.parseInt(splitDate[i]) < 0 || Integer.parseInt(splitDate[i]) > 9999)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("")) {
                            date.setYear(2021);
                        } else {
                            date.setYear(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "hh":
                        if (getCountsOfDigits(Integer.parseInt(splitDate[i])) != 2 || Integer.parseInt(splitDate[i]) < 0)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("00")) {
                            date.setHour(0);
                        } else {
                            date.setHour(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "mn":
                        if (getCountsOfDigits(Integer.parseInt(splitDate[i])) != 2 || Integer.parseInt(splitDate[i]) < 0)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("00")) {
                            date.setMinute(0);
                        } else {
                            date.setMinute(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "ss":
                        if (getCountsOfDigits(Integer.parseInt(splitDate[i])) != 2 || Integer.parseInt(splitDate[i]) < 0)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("00")) {
                            date.setSecond(0);
                        } else {
                            date.setSecond(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    case "ml":
                        if (getCountsOfDigits(Integer.parseInt(splitDate[i])) != 2 || Integer.parseInt(splitDate[i]) < 0)
                            throw new FormatDateException("Incorrect input by format");
                        if (splitDate[i].equals("00")) {
                            date.setMillisecond(0);
                        } else {
                            date.setMillisecond(Integer.parseInt(splitDate[i]));
                        }
                        break;
                    default:
                        continue;
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
        if (checkNumbersInMonthAndYear(date))
            return date;
        else {
            throw new FormatDateException();
        }
    }

    public String getDateByFormat(Date date, String format) {
        String dateFromFormat = "";
        char c;
        String splitFormat[] = format.split("/|\\s|:|-");
        for (int i = 0; i < splitFormat.length; i++) {
            switch (splitFormat[i]) {
                case "d":
                    int index = format.indexOf("d");
                    dateFromFormat += date.getDay();
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "dd":
                    index = format.lastIndexOf("d");
                    if (date.getDay() < 10)
                        dateFromFormat += "0" + date.getDay();
                    else
                        dateFromFormat += date.getDay();
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "m":
                    index = format.indexOf("m");
                    dateFromFormat += date.getMonth();
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "mm":
                    index = format.lastIndexOf("m");
                    if (date.getMonth() < 10)
                        dateFromFormat += "0" + date.getMonth();
                    else
                        dateFromFormat += date.getMonth();
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "mmm":
                    index = format.lastIndexOf("m");
                    int month = date.getMonth();
                    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                    for (int j = 0; j < months.length; j++) {
                        if (month == j) {
                            dateFromFormat += months[j--];
                            break;
                        }
                    }
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "yy":
                    index = format.lastIndexOf("y");
                    int yy = date.getYear() % 100;
                    if (yy < 10) {
                        dateFromFormat += "0" + yy;
                    } else {
                        dateFromFormat += yy;
                    }
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "yyyy":
                    index = format.lastIndexOf("y");
                    dateFromFormat += date.getYear();
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "hh":
                    index = format.lastIndexOf("h");
                    int hh = date.getHour();
                    if (hh < 10)
                        dateFromFormat += "0" + hh;
                    else
                        dateFromFormat += hh;
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "mn":
                    index = format.lastIndexOf("n");
                    int mn = date.getMinute();
                    if (mn < 10)
                        dateFromFormat += "0" + mn;
                    else
                        dateFromFormat += mn;
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "ss":
                    index = format.lastIndexOf("s");
                    int ss = date.getSecond();
                    if (ss < 10)
                        dateFromFormat += "0" + ss;
                    else
                        dateFromFormat += ss;
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                case "ml":
                    index = format.lastIndexOf("l");
                    int ms = date.getMillisecond();
                    if (ms < 10)
                        dateFromFormat += "0" + ms;
                    else
                        dateFromFormat += ms;
                    c = checkDelimiter(index, format);
                    if (c != 0) {
                        dateFromFormat += c;
                    }
                    break;
                default:
                    continue;
            }
        }
        return dateFromFormat;
    }

    public int getCountsOfDigits(int number) {
        int count = (number == 0) ? 1 : 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }


    public boolean checkNumbersInMonthAndYear(Date date) {
        int year = date.getYear();
        int month = date.getMonth();
        int days = date.getDay();
        int h = date.getHour();
        int mn = date.getMinute();
        int ss = date.getMinute();
        int ml = date.getMillisecond();
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)) && month == 2) {
            if (days > 0 && days < 29) {
                if (h >= 0 && h < 60 && mn >= 0 && mn < 60 && ss >= 0 && ss < 60 & ml >= 0 && ml < 60) {
                    return true;
                }
            }
        }
        if (!(year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            if (days > 0 && days < countDaysInMonth(month)) {
                if (h >= 0 && h < 60 && mn >= 0 && mn < 60 && ss >= 0 && ss < 60 & ml >= 0 && ml < 60) {
                    return true;
                }
            }
        }
        return false;
    }


    public int countDaysInMonth(int month) {
        return (int) (28 + (month + Math.floor(month / 8)) % 2 + 2 % month + 2 * Math.floor(1 / month));
    }

    public char checkDelimiter(int index, String format) {
        char c;
        if (index + 1 != format.length()) {
            c = format.charAt(index + 1);
            if (c == '-') {
                c = ' ';
            }
            return c;
        }
        return 0;
    }
}
