package ua.com.alevel.firstLevel.checkKnightMove;

import static ua.com.alevel.Main.scan;

public class KnightMoveData {

    public void coordinatesOfFigureAndChecking(){
        System.out.print("Current position" + "\nEnter the horizontal coordinate: ");
        int xStart = inputAndCheckCorrectCoord();
        System.out.print("Enter the vertical coordinate: ");
        int yStart = inputAndCheckCorrectCoord();
        System.out.print("Expected position" + "\nEnter the horizontal coordinate: ");
        int xEnd = inputAndCheckCorrectCoord();
        System.out.print("Enter the vertical coordinate: ");
        int yEnd = inputAndCheckCorrectCoord();
        CheckingKnightMove checkingKnightMove = new CheckingKnightMove();
        if (!checkingKnightMove.checkingMove(xStart, yStart, xEnd, yEnd))
            System.out.println("Knight's unacceptable move");
        else
            System.out.println("Knight's acceptable move");
    }

    private static int inputAndCheckCorrectCoord(){
        int coord = scan.nextInt();
        if (coord == 0){
            System.out.print("Uncorrected entered coordinate. Try again: ");
            return inputAndCheckCorrectCoord();
        }
        if (coord < 0) {
            coord--;
            return Math.abs(coord);
        }
        else
            return Math.abs(coord);
    }
}
