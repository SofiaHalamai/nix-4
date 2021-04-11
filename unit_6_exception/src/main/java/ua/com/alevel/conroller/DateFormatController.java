package ua.com.alevel.conroller;

import ua.com.alevel.entity.Date;
import ua.com.alevel.exception.FormatDateException;
import ua.com.alevel.util.CheckCorrectFormatOfDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DateFormatController {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    CheckCorrectFormatOfDate correctFormatOfDate = new CheckCorrectFormatOfDate();

    public Date runFormat() throws IOException, FormatDateException {
        try {
            Date date;
            System.out.println("\tFORMAT OF DATE");
            System.out.print("Enter format: ");
            String format = bufferedReader.readLine();
            System.out.print("Enter date in format: ");
            String dateInFormat = bufferedReader.readLine();
            date = correctFormatOfDate.createDateByFormat(format, dateInFormat);
            if (date == null) {
                runFormat();
                throw new FormatDateException("Incorrect input be format");
            }
            return date;
        } catch (FormatDateException e) {
            throw new FormatDateException(e.getMessage());
        }
    }

    public String printFormat(Date date) throws IOException, FormatDateException {
        String dateStr;
        System.out.println("\tFORMAT OF DATE");
        System.out.print("Enter format: ");
        String format = bufferedReader.readLine();
        dateStr = correctFormatOfDate.getDateByFormat(date, format);
        return dateStr;
    }
}

