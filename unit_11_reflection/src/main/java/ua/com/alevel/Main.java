package ua.com.alevel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        AppProperties appProps;
        Properties property = new Properties();
        try (InputStream input = Main.class.getResourceAsStream("/app.properties")) {
            property.load(input);
        } catch (IOException e) {
            System.out.println("File read error");
        }
        appProps = new FieldInitializer().assignmentOfValues(AppProperties.class, property);
        System.out.println(appProps.toString());
    }
}
