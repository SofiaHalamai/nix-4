package ua.com.alevel.entity;

import ua.com.alevel.annotation.ValueOfCell;

public class DataCsv {

    @ValueOfCell("ID")
    private int id;

    @ValueOfCell("FirstName")
    public String firstName;

    @ValueOfCell("LastName")
    public String lastName;

    @ValueOfCell("Age")
    private int age;

    @ValueOfCell("Points")
    public double numberOfPoints;

    @Override
    public String toString() {
        return "DataCsv {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", numberOfPoints=" + numberOfPoints +
                '}';
    }
}
