package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.ProblemDao;
import ua.com.alevel.model.Problem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.db.ConnectionToDb.getConnectionToDb;


public class JdbcProblemDao implements ProblemDao {

    @Override
    public void addProblemToDb(Problem problem) {
        try {
            PreparedStatement prSt = getConnectionToDb().prepareStatement(
                    "INSERT INTO problems (from_id, to_id) VALUES (?, ?)");
            prSt.setInt(1, problem.getFromId());
            prSt.setInt(2, problem.getToId());
            prSt.executeQuery();
        } catch (SQLException e) {
            System.out.println("This data is already stored in DB");
        }
    }

    @Override
    public List<Problem> getAllProblems() {
        List <Problem> problems = new ArrayList<>();
        ResultSet resSet;
        try  {
            PreparedStatement prSt = getConnectionToDb().prepareStatement(
                    "SELECT * FROM problems");
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                problems.add(new Problem(resSet.getInt("id"), resSet.getInt("from_id"),
                        resSet.getInt("to_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return problems;
    }
}
