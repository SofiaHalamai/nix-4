package ua.com.alevel;

import ua.com.alevel.entity.AppProperties;
import ua.com.alevel.workWithProperties.FieldInitializer;
import ua.com.alevel.workWithProperties.Loader;

public class Main {

    public static void main(String[] args) {
        AppProperties appProps = new FieldInitializer().assignmentOfValues(AppProperties.class,
                new Loader().propertiesLoader("/app.properties"));
        System.out.println(appProps.toString());
    }
}
