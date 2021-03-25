package ua.com.alevel.firstLevel.calcAreaOfTriangle;

import java.awt.*;

public class CalculationAreaOfTriangle {
    Point a;
    Point b;
    Point c;

    public CalculationAreaOfTriangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double calcArea (Point a, Point b, Point c){
        double area;
        return area = Math.abs((a.getX()-c.getX())*(b.getY()-a.getY())-(a.getX()-b.getX())*(c.getY()-a.getY()))*0.5;
    }
}
