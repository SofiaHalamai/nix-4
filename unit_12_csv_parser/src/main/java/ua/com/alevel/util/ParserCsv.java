package ua.com.alevel.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class ParserCsv {

    public static String[][] getMatrixFromCsv(){
        LinkedList<String[]> rows = new LinkedList<>();
        String[] dataRow;
        try (InputStream input = ParserCsv.class.getResourceAsStream("/data.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(input))) {
            while ((dataRow = reader.readNext()) != null) {
                rows.addLast(dataRow);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("File access error, the path may be incorrect!");
        }
        return rows.toArray(new String[rows.size()][]);
    }

}



