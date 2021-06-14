package ua.com.alevel.hibernate.util;

import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;

public class ConnectToDb {

    public static Configuration connectingWithPasswordAndLoginToDB(BufferedReader reader) throws IOException {
        System.out.println("Enter username db: ");
        String username = reader.readLine();
        System.out.println("Enter password db: ");
        String password = reader.readLine();
        return new Configuration()
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .configure();
    }
}
