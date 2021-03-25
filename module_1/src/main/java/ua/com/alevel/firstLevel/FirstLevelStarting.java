package ua.com.alevel.firstLevel;

import ua.com.alevel.firstLevel.calcAreaOfTriangle.PointsOfTriangle;
import ua.com.alevel.firstLevel.checkKnightMove.KnightMoveData;
import ua.com.alevel.firstLevel.numberUniqueSymbols.ArrayDataProcessing;

import static ua.com.alevel.Main.scan;

public class FirstLevelStarting {

    public void firstLevelRun(){
        boolean tmp = true;
        while (tmp) {
            System.out.print("\tFIRST LEVEL" + "\n" + "1. Search for unique symbols in the array" + "\n" +
                                "2. Checking the knight's move" + "\n" +
                                "3. Calculating the area of a triangle" + "\n" +
                                "4. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = scan.next();
            switch (position) {
                case "1":
                    ArrayDataProcessing arrayDataProcessing = new ArrayDataProcessing();
                    arrayDataProcessing.searchAllUniqueSymbols();
                    break;
                case "2":
                    KnightMoveData knightMoveData = new KnightMoveData();
                    knightMoveData.coordinatesOfFigureAndChecking();
                    break;
                case "3":
                    PointsOfTriangle pointsOfTriangle = new PointsOfTriangle();
                    pointsOfTriangle.startCalcAreaOfTriangle();
                    break;
                case "4":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }
}
