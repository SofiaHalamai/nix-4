package ua.com.alevel.entity;

import java.util.Arrays;
import java.util.List;


public class TableFromCsv {

    private final List<String> headlines;

    private final int rows;

    private final int columns;

    private final String[][] csvMatrix;


    public TableFromCsv(String[][] cells) {
        this.headlines = Arrays.asList(cells[0]);
        this.csvMatrix = cells;
        this.rows = csvMatrix.length;
        this.columns = headlines.size();
    }

    public List<String> getHeadlines() {
        return headlines;
    }

    public String getCell(int indexRow, int indexColumn) {
        return csvMatrix[indexRow + 1][indexColumn];
    }

    public String getCell(int indexRow, String headline) {
        return csvMatrix[indexRow + 1][headlines.indexOf(headline)];
    }

    public int sizeOfRows() {
        return rows;
    }
}
