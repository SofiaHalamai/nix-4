package ua.com.alevel.thirdLevel.conwaysGameOfLife.data;

import java.awt.*;

public class DataStore {
    public  static final int SIZE_CELL = 20;
    public  static final int WIDTH_FIELD = 40;
    public  static final int HEIGHT_FIELD = 30;
    public  static final int WAIT_MS = 500;

    public  static Color getColor (CellStatus status){
        switch (status) {
            default:
            case NONE: return Color.BLACK;
            case BORN: return Color.GRAY;
            case LIVE: return Color.GREEN;
            case DIED: return Color.BLACK;
        }
    }
}
