package ua.com.alevel.firstLevel.checkKnightMove;

public class CheckingKnightMove {

    public boolean checkingMove(int xStart, int yStart, int xEnd, int yEnd){
        int x = Math.abs(xStart - xEnd);
        int y = Math.abs(yStart - yEnd);
        if (x == 1 && y == 2 || x == 2 && y == 1)
            return true;
        else
            return false;
    }
}
