package ua.com.alevel.service;

import ua.com.alevel.entity.Date;
import ua.com.alevel.exception.FormatDateException;

import java.util.List;

public interface Calendar {

    boolean leapYearCheck (int year);
    long countOfMillisecondInDate (Date date);
    int countDaysInMonth (int month);
    void differenceBetweenDate (Date startDate, Date finishDate) throws FormatDateException;

    Date addTimeToDate(Date date);
    Date addMsToDate(Date date, int ms);
    Date addSecondsToDate(Date date, int second);
    Date addMinutesToDate(Date date, int minute);
    Date addHoursToDate(Date date, int hour);
    Date addYearsToDate(Date date, int year);
    Date addCenturiesToDate(Date date, int century);

    Date subtractTimeToDate(Date date, long time) throws FormatDateException;
    Date subtractYearsToDate (Date date, long time);
    List<Date> sortAscending(Date [] arr);
    List<Date> sortDescending(Date [] arr);
}
