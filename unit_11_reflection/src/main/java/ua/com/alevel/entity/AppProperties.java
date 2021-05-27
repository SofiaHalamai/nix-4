package ua.com.alevel.entity;

import ua.com.alevel.annotation.PropertyKey;

public class AppProperties {

    @PropertyKey("first_name")
    public String firstName;

    @PropertyKey("last_name")
    public String lastName;

    @PropertyKey("person's_age")
    public int age;

    @PropertyKey("status")
    public boolean active;

    @PropertyKey("points")
    public double numberOfPoints;

    @Override
    public String toString() {
        return "AppProperties {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", numberOfPoints=" + numberOfPoints +
                '}';
    }
}
