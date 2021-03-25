package ua.com.alevel.thirdLevel.conwaysGameOfLife.service;

import ua.com.alevel.thirdLevel.conwaysGameOfLife.data.DataStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Box extends JPanel {

    public Cell cell;

    public Box(int x, int y) {
        super();
        cell = new Cell();
        setBounds(x * DataStore.SIZE_CELL, y * DataStore.SIZE_CELL, DataStore.SIZE_CELL, DataStore.SIZE_CELL);
        setBackground(Color.GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.changeCellTurn();
            }
        });
    }

    public void setColor() {
        setBackground(DataStore.getColor(cell.status));
    }

    public void step1() {
        cell.step1();
        setColor();
    }

    public void step2 (){
        cell.step2();
        setColor();
    }
}
