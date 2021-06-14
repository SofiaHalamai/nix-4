package ua.com.alevel.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ua.com.alevel.jdbc.service.impl.JDBCCommonServiceImpl.logger;

public class ConnectionToDB {

    public Connection getConnectionToDb(String user,String password) {
        Connection connection = null;
        try{
            String url = "jdbc:postgresql://localhost:5432/finances";
            connection = DriverManager.getConnection(url, user, password);
            logger.info ("Connected to the PostgreSQL server successfully!");
        } catch (SQLException e){
            logger.error("Error connection", e);
        }
        return connection;
    }
}
