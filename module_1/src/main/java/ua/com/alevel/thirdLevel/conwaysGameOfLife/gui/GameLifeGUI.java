package ua.com.alevel.thirdLevel.conwaysGameOfLife.gui;

import ua.com.alevel.thirdLevel.conwaysGameOfLife.service.Box;
import ua.com.alevel.thirdLevel.conwaysGameOfLife.data.DataStore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLifeGUI implements Runnable {

    JFrame frame;
    Box[][] boxes;

    @Override
    public void run() {
        createFrame();
        createBoxes();
        initTimer();
    }

    private void createFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(DataStore.SIZE_CELL * DataStore.WIDTH_FIELD, DataStore.SIZE_CELL * DataStore.HEIGHT_FIELD);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Game of Life");
    }

    private void createBoxes() {
        boxes = new Box[DataStore.WIDTH_FIELD][DataStore.HEIGHT_FIELD];
        for (int x = 0; x < DataStore.WIDTH_FIELD; x++){
            for (int y = 0; y < DataStore.HEIGHT_FIELD; y++) {
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }
        }
        for (int x = 0; x < DataStore.WIDTH_FIELD; x++) {
            for (int y = 0; y < DataStore.HEIGHT_FIELD; y++) {
                for (int dx = -1; dx <= +1; dx++){
                    for (int dy = -1; dy <= +1; dy++){
                        if (!(dx == 0 && dy == 0)){
                            boxes[x][y].cell.addNear(boxes
                            [(x + dx + DataStore.WIDTH_FIELD) % DataStore.WIDTH_FIELD]
                            [(y + dy + DataStore.HEIGHT_FIELD) % DataStore.HEIGHT_FIELD].cell);
                        }
                    }
                }
            }
        }
    }

    private void initTimer(){
        TimerListener listener = new TimerListener();
        Timer time = new Timer(DataStore.WAIT_MS, listener);
        time.start();
    }

    private class TimerListener implements ActionListener {
        boolean floap = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            floap = !floap;
            for (int x = 0; x < DataStore.WIDTH_FIELD; x++)
                for (int y = 0; y < DataStore.HEIGHT_FIELD; y++)
                {
                    if (floap)
                        boxes[x][y].step1();
                    else
                        boxes[x][y].step2();
                }
        }
    }
}
