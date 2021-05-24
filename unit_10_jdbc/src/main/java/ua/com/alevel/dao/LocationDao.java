package ua.com.alevel.dao;

import ua.com.alevel.model.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface LocationDao {

    public int getNumberOfLocation() throws SQLException;
    public void addLocationToDb( List<Location> cities);
}
