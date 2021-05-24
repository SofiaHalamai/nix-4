package ua.com.alevel.dao;

import ua.com.alevel.model.Problem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProblemDao {

    public void addProblemToDb(Problem problem);
    public List <Problem> getAllProblems();

}
