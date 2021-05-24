package ua.com.alevel.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionToDb {

    public static Connection getConnectionToDb() {
        Connection connection = null;
        Properties properties = getProperties();
        String url = properties.getProperty("url");
        try{
            connection = DriverManager.getConnection(url, properties);
            System.out.println("Connected to the PostgreSQL server successfully!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try(InputStream input = ConnectionToDb.class.getResourceAsStream("/jdbc.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return properties;
    }
}
