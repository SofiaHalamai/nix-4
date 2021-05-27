package ua.com.alevel.workWithProperties;

import ua.com.alevel.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Loader {

    public Properties propertiesLoader (String path){
        Properties property = new Properties();
        try (InputStream input = Main.class.getResourceAsStream(path)) {
            property.load(input);
        } catch (IOException e) {
            System.out.println("File read error");
        }
        return property;
    }
}
