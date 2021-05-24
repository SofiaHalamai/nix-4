package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.SolutionDao;
import ua.com.alevel.model.Solution;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.com.alevel.db.ConnectionToDb.getConnectionToDb;

public class JdbcSolutionDao implements SolutionDao {

    @Override
    public void setSolution(List<Solution> solutions) {
        try (PreparedStatement prSt = getConnectionToDb().prepareStatement(
                "INSERT INTO solutions (problem_id, cost) VALUES (?, ?)")) {
            for (Solution solution : solutions) {
                prSt.setInt(1, solution.getProblemId());
                prSt.setInt(2, solution.getCost());
                prSt.addBatch();
            }
            prSt.executeBatch();
        } catch (SQLException e) {
            System.out.println("Solution already exists!");
        }
    }
}


