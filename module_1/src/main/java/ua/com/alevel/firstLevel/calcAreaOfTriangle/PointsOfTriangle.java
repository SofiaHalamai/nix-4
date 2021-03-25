package ua.com.alevel.firstLevel.calcAreaOfTriangle;

import java.awt.*;
import static ua.com.alevel.Main.scan;

public class PointsOfTriangle {

    public void startCalcAreaOfTriangle (){
        System.out.println("Coordinates of the first vertex");
        Point a = createPoint();
        System.out.println("Coordinates of the second vertex");
        Point b = createPoint();
        System.out.println("Coordinates of the third vertex");
        Point c = createPoint();
        CalculationAreaOfTriangle calculationAreaOfTriangle = new CalculationAreaOfTriangle(a, b, c);
        System.out.println("Result area of triangle: " + calculationAreaOfTriangle.calcArea(a, b, c));
    }

    private static Point createPoint (){
        System.out.print("Enter the coordinate of the vertex X = ");
        int x = scan.nextInt();
        System.out.print("Enter the coordinate of the vertex Y = ");
        int y = scan.nextInt();
        Point tmp = new Point(x, y);
        return tmp;
    }
}
