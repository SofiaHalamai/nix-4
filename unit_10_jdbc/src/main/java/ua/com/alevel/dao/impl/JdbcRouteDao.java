package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.RouteDao;
import ua.com.alevel.model.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.db.ConnectionToDb.getConnectionToDb;


public class JdbcRouteDao implements RouteDao {

    @Override
    public void addRouteToDb(int[][] linkMatrix) {
        try (PreparedStatement prSt = getConnectionToDb().prepareStatement(
                "INSERT INTO routes (from_id, to_id, cost) VALUES (?, ?, ?)")) {
            for (int i = 0; i < linkMatrix.length; i++) {
                for (int j = 0; j < linkMatrix.length; j++) {
                    if (linkMatrix[i][j] == 0) continue;
                    prSt.setInt(1, i + 1);
                    prSt.setInt(2, j + 1);
                    prSt.setInt(3, linkMatrix[i][j]);
                    prSt.addBatch();
                }
            }
            prSt.executeBatch();
        } catch (SQLException e) {
            System.out.println("This data is already stored in DB");
        }
    }

    @Override
    public List<Route> getAllRoute() {
        List <Route> routes = new ArrayList<>();
        ResultSet resSet;
        try (PreparedStatement prSt = getConnectionToDb().prepareStatement(
                "SELECT * FROM routes")){
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                routes.add(new Route(resSet.getInt("id"), resSet.getInt("from_id"),
                        resSet.getInt("to_id"), resSet.getInt("cost")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }
}

