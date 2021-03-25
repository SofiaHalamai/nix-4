package ua.com.alevel.thirdLevel;

import ua.com.alevel.thirdLevel.conwaysGameOfLife.gui.GameLifeGUI;

import javax.swing.*;
import static ua.com.alevel.Main.scan;

public class ThirdLevelStarting {

    public void thirdLevelRun(){
        boolean tmp = true;
        while (tmp) {
            System.out.print("\tTHIRD LEVEL" + "\n" + "1. Game of Life" + "\n" +
                    "2. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    GameLifeGUI gameLifeGUI = new GameLifeGUI();
                    SwingUtilities.invokeLater(gameLifeGUI);
                    System.out.println("The game of life opens in a new window");
                    break;
                case "2":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }
}
