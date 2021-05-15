package ua.com.alevel.firstTask;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ua.com.alevel.controller.MainController.*;

public class CheckingDatesForFormat {

    public static List<String> findCorrectDate (List<String> dates){
        Pattern regex1 = Pattern.compile("^(19|20)[0-9]{2}[/](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])$"); //yyyy/mm/dd
        Pattern regex2 = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)[0-9]{2}$"); //dd/mm/yyyy
        Pattern regex3 = Pattern.compile("^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)[0-9]{2}$"); //mm-dd-yyyy
        List<String> filterDates = dates.stream()
                .filter(c->{
                    String f = c.replaceAll(regex1.toString(), "");
                    String s = c.replaceAll(regex2.toString(), "");
                    String t = c.replaceAll(regex3.toString(), "");
                    return !f.equals(c) || !s.equals(c) || !t.equals(c);
                })
                .collect(Collectors.toList());
        filterDates = deleteDelimiters(filterDates);
        return filterDates;
    }

    private static List<String> deleteDelimiters (List<String> filterDates) {
        List<String> result = new ArrayList<>();
        for (String tmp : filterDates) {
            if (tmp.contains("/") && !tmp.contains("-"))
                result.add(tmp.replaceAll("/", ""));
            if (tmp.contains("-") && !tmp.contains("/"))
                result.add(tmp.replaceAll("-", ""));
        }
        System.out.println(ANSI_BLUE + "THE RESULT OF FINDING DATES IN THE CORRESPONDING FORMATS (WITHOUT DELIMITERS):  " + ANSI_RESET + result);
        return result;
    }
}
