package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.LocationDao;
import ua.com.alevel.model.Location;

import java.sql.*;
import java.util.List;

import static ua.com.alevel.db.ConnectionToDb.getConnectionToDb;

public class JdbcLocationDao implements LocationDao {

    @Override
    public int getNumberOfLocation() throws SQLException {
        ResultSet result;
        int N = 0;
        try {
            PreparedStatement prSt = getConnectionToDb().prepareStatement("SELECT * FROM locations WHERE id  = (SELECT MAX (id) FROM locations)");
            result = prSt.executeQuery();
            while (result.next()) {
                N = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return N;
    }

    @Override
    public void addLocationToDb(List<Location> cities) {
        try {
            PreparedStatement prSt = getConnectionToDb().prepareStatement(
                    "INSERT INTO locations (name) VALUES (?)");
            for (Location city : cities) {
                prSt.setString(1, city.getName());
                prSt.addBatch();
            }
            prSt.executeBatch();
        } catch (SQLException e) {
            System.out.println("This data is already stored in DB");
        }
    }
}
