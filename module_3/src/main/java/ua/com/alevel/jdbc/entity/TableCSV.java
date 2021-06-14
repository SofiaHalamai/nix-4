package ua.com.alevel.jdbc.entity;

public class TableCSV {

    private final String[] headers;

    private final String[][] cells;

    public TableCSV(String[] headers, String[][] cells) {
        this.cells = cells;
        this.headers = headers;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[][] getCells() {
        return cells;
    }
}

