package ua.com.alevel.thirdLevel.conwaysGameOfLife.service;

import ua.com.alevel.thirdLevel.conwaysGameOfLife.data.CellStatus;
import java.util.ArrayList;

public class Cell {
    ArrayList<Cell> near;
    CellStatus status;

    public  Cell() {
        status = CellStatus.NONE;
        near = new ArrayList<>();
    }

    public void addNear(Cell cell) {
        near.add(cell);
    }

    public void step1() {
        int around = countNearCells();
        status = status.prepare(around);
    }

    public void step2() {
        status = status.replace();
    }

    public int countNearCells() {
        int count = 0;
        for (Cell cell: near)
            if (cell.status.isCell())
                count++;
        return count;
    }

    public void changeCellTurn() {
        for (Cell cell : near)
            cell.status = cell.status.isCell() ? CellStatus.NONE : CellStatus.LIVE;
    }
}
