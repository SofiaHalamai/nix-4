package ua.com.alevel;

import ua.com.alevel.entity.DataCsv;
import ua.com.alevel.entity.TableFromCsv;
import ua.com.alevel.util.FieldInitializer;

import java.util.List;

import static ua.com.alevel.util.ParserCsv.getMatrixFromCsv;

public class Main {

    public static void main(String[] args) {
        TableFromCsv tableFromCsv = new TableFromCsv(getMatrixFromCsv());
        List<DataCsv> dataCsvList = new FieldInitializer().assignmentOfValues (DataCsv.class, tableFromCsv);
        for (DataCsv dataCsv : dataCsvList) {
            System.out.println(dataCsv);
        }
    }
}
