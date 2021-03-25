package ua.com.alevel.conroller;

import ua.com.alevel.firstLevel.FirstLevelStarting;
import ua.com.alevel.secondLevel.SecondLevelStarting;
import ua.com.alevel.thirdLevel.ThirdLevelStarting;
import ua.com.alevel.thirdLevel.conwaysGameOfLife.gui.GameLifeGUI;

import javax.swing.*;

import static ua.com.alevel.Main.scan;

public class MainController {

    public void run(){
        while (true) {
            System.out.print("\tMODULE 1" + "\n" + "1. First level (3 tasks)" + "\n" +
                    "2. Second level (1 task)" + "\n" +
                    "3. Third Level (1 task)" + "\n" +
                    "4. Exit" + "\n" +"Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    FirstLevelStarting firstLevelStarting = new FirstLevelStarting();
                    firstLevelStarting.firstLevelRun();
                    break;
                case "2":
                    SecondLevelStarting secondLevelStarting = new SecondLevelStarting();
                    secondLevelStarting.secondLevelRun();
                    break;
                case "3":
                    ThirdLevelStarting thirdLevelStarting = new ThirdLevelStarting();
                    thirdLevelStarting.thirdLevelRun();
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }
}
