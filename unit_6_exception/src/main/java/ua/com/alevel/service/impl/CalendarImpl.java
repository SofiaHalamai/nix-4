package ua.com.alevel.service.impl;

import ua.com.alevel.entity.Date;
import ua.com.alevel.exception.FormatDateException;
import ua.com.alevel.service.Calendar;

import java.util.Arrays;
import java.util.List;


public class CalendarImpl implements Calendar {

    final int COUNT_DAYS_IN_LEAP_YEAR = 366;
    final int COUNT_DAYS_IN_YEAR = 365;
    final long COUNT_MS_IN_DAY = 86400000L;
    final long COUNT_MS_IN_HOUR = 3600000L;
    final long COUNT_MS_IN_MINUTE = 60000L;
    final long COUNT_MS_IN_SECOND = 1000L;

    @Override
    public boolean leapYearCheck(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    @Override
    public long countOfMillisecondInDate(Date date) {
        int commonCountDays = 0;
        int commonCountDaysInMonth;
        for (int i = 0; i < date.getYear(); i++){
            if (i != 0 && leapYearCheck(i))
                commonCountDays += COUNT_DAYS_IN_LEAP_YEAR;
            else
                commonCountDays += COUNT_DAYS_IN_YEAR;
        }
        for (int i = 1; i < date.getMonth(); i++){
            if (leapYearCheck(date.getYear()) && date.getMonth() == 2){
                commonCountDays += 29;
            }
            else {
                commonCountDaysInMonth = countDaysInMonth(i);
                commonCountDays += commonCountDaysInMonth;
            }
        }
        commonCountDays += date.getDay();
        return (long) COUNT_MS_IN_DAY * commonCountDays + COUNT_MS_IN_HOUR * date.getHour() + COUNT_MS_IN_MINUTE * date.getMinute() + COUNT_MS_IN_SECOND * date.getSecond() + date.getMillisecond();
    }



    @Override
    public int countDaysInMonth(int month) {
        return (int) (28 + (month + Math.floor(month / 8)) % 2 + 2 % month + 2 * Math.floor(1 / month));
    }

    @Override
    public void differenceBetweenDate(Date startDate, Date finishDate) throws FormatDateException {
        long firstDateInMs = countOfMillisecondInDate(startDate);
        long secondDateInMs = countOfMillisecondInDate(finishDate);
        long differenceInMs = secondDateInMs - firstDateInMs;
        int years = (int) Math.floor(differenceInMs / (COUNT_MS_IN_DAY*COUNT_DAYS_IN_YEAR));
        int centuries = centuryFromYear(years);
        int months = (int) Math.floor(differenceInMs / (1000L * 60 * 60 * 24 * 30));
        int days = (int) Math.floor(differenceInMs / COUNT_MS_IN_DAY);
        int hours = (int) Math.floor((differenceInMs / COUNT_MS_IN_HOUR));
        int minutes = (int) Math.floor((differenceInMs / COUNT_MS_IN_MINUTE));
        long seconds = (long) Math.floor((differenceInMs / COUNT_MS_IN_SECOND));
        long milliseconds = differenceInMs;
        if (years < 0 || centuries < 0 || months < 0 || days < 0 || hours < 0 || minutes < 0 || seconds < 0 || milliseconds < 0){
            throw new FormatDateException ("Incorrect input one date is greater than the other and negative values are obtained");
        }
        System.out.println("Difference between dates in: " + centuries + " centuries, "
                + years + " years, "
                + months + " months, " + days + " days, "
                + hours + " hours, " + minutes + " minutes, "
                + seconds + " seconds, " + milliseconds + " milliseconds");
    }

    @Override
    public Date addTimeToDate(Date date) {
        int century = date.getCenturies();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int minute = date.getMinute();
        int hour = date.getHour();
        int second = date.getSecond();
        int millisecond = date.getMillisecond();
        if (millisecond > 1000){
            second += millisecond / 1000;
            millisecond = millisecond % 1000;
        }
        date.setMillisecond(millisecond);
        if (second > 60){
            minute += second / 60;
            second = second % 60;
        }
        date.setSecond(second);
        if (minute > 60){
            hour += minute / 60;
            minute = minute % 60;
        }
        date.setMinute(minute);
        if (hour > 24){
            day += hour / 24;
            hour = hour % 24;
        }
        date.setHour(hour);
        int daysInMonth = countDaysInMonth(month);
        if (day > daysInMonth){
            month += day / daysInMonth;
            day = day % daysInMonth;
        }
        date.setDay(day);
        if (month > 12) {
            year += month / 12;
            month = month % 12;
        }
        date.setMonth(month);
        int tmpCentury = centuryFromYear(year);
        if (tmpCentury != century){
            year += (century-tmpCentury)*100;
        }
        date.setYear(year);
        date.setCenturies(century);
        return date;
    }

    @Override
    public Date addMsToDate(Date date, int ms) {
        int currentMs = date.getMillisecond();
        date.setMillisecond(currentMs + ms);
        date.setCenturies(centuryFromYear(date.getYear()));
        return date;
    }

    @Override
    public Date addSecondsToDate(Date date, int second) {
        int currentSecond = date.getSecond();
        date.setSecond(currentSecond + second);
        date.setCenturies(centuryFromYear(date.getYear()));
        return date;
    }

    @Override
    public Date addMinutesToDate(Date date, int minute) {
        int currentMinute = date.getMinute();
        date.setMinute(currentMinute + minute);
        date.setCenturies(centuryFromYear(date.getYear()));
        return date;
    }

    @Override
    public Date addHoursToDate(Date date, int hour) {
        int currentHour = date.getHour();
        date.setHour(currentHour + hour);
        date.setCenturies(centuryFromYear(date.getYear()));
        return date;
    }

    @Override
    public Date addYearsToDate(Date date, int year) {
        int currentYear = date.getYear();
        date.setYear(currentYear + year);
        date.setCenturies(centuryFromYear(currentYear + year));
        return date;
    }

    @Override
    public Date addCenturiesToDate(Date date, int century) {
        int currentCentury = centuryFromYear(date.getYear());
        date.setCenturies(currentCentury + century);
        return date;
    }

    @Override
    public Date subtractTimeToDate(Date date, long time) throws FormatDateException {
        while (time > date.getMillisecond()) {
            date.setMillisecond(date.getMillisecond() + 1000);
            date.setSecond(date.getSecond() - 1);
            if (date.getSecond() <= 0) {
                date.setSecond(date.getSecond() + 60);
                date.setMinute(date.getMinute() - 1);
                if (date.getMinute() <= 0) {
                    date.setMinute(date.getMinute() + 60);
                    date.setHour(date.getHour() - 1);
                    if (date.getHour() <= 0) {
                        date.setHour(date.getHour() + 24);
                        date.setDay(date.getDay() - 1);
                        if (date.getDay() <= 0) {
                            date.setDay(date.getDay() + countDaysInMonth(date.getMonth()));
                            date.setMonth(date.getMonth() - 1);
                            if (date.getMonth() <= 0) {
                                date.setMonth(date.getMonth() + 12);
                                date.setYear(date.getYear() - 1);
                                if (date.getYear() <= 0) {
                                    throw new FormatDateException ("Incorrect input. Negative values are obtained");
                                }
                            }
                        }
                    }
                }
            }
        }
        date.setMillisecond((int) (date.getMillisecond() - time));
        return date;
    }

    @Override
    public Date subtractYearsToDate(Date date, long time) {
        if (time > date.getYear()){
            return null;
        }else {
            date.setYear((int) (date.getYear() - time));
            return date;
        }
    }


    @Override
    public List<Date> sortAscending(Date[] arr) {
        Date tmpDate;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (countOfMillisecondInDate(arr[j]) > countOfMillisecondInDate(arr[j + 1])) {
                    tmpDate = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmpDate;
                }
            }
        }
        return Arrays.asList(arr);
    }

    @Override
    public List<Date> sortDescending(Date[] arr) {
        Date tmpDate;
        for(int i = 1; i < arr.length; ++i) {
            for(int j = 0; j < arr.length-i; j++) {
                if(countOfMillisecondInDate(arr[j]) < countOfMillisecondInDate(arr[j+1])) {
                    tmpDate = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmpDate;
                }
            }
        }
        return Arrays.asList(arr);
    }


    private int centuryFromYear(int year) {
        if (year % 100 == 0) {
             return year / 100;
        } else {
            return (year / 100) + 1;
        }
    }
}
