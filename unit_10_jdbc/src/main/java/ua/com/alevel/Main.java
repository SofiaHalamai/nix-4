package ua.com.alevel;

import ua.com.alevel.dao.impl.JdbcLocationDao;
import ua.com.alevel.dao.impl.JdbcProblemDao;
import ua.com.alevel.dao.impl.JdbcRouteDao;
import ua.com.alevel.dao.impl.JdbcSolutionDao;
import ua.com.alevel.model.Problem;
import ua.com.alevel.model.Route;
import ua.com.alevel.model.Solution;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.util.FindingPathBetweenCities.findMinPath;

public class Main {
    public static void main(String[] args) {
        int result;
        List<Solution> solutionList = new ArrayList<>();
        JdbcSolutionDao solutionDao = new JdbcSolutionDao();
        JdbcLocationDao locationDao = new JdbcLocationDao();
        JdbcRouteDao routeDao = new JdbcRouteDao();
        JdbcProblemDao problemDao = new JdbcProblemDao();
        int N = 0;
        // adding information to an empty database from a file
        // the method is designed exclusively for a one-time addition
//        AddInformationToDB addInformationToDB = new AddInformationToDB();
//        addInformationToDB.addDataToDbFromFile();

        try {
            N = locationDao.getNumberOfLocation();
        } catch (SQLException throwables) {
            System.out.println("The request failed");
        }

        int[][] linkMatrix = new int[N][N];

        List<Route> routes = routeDao.getAllRoute();
        for (Route route : routes) {
            int i = route.getFromId();
            int j = route.getToId();
            linkMatrix[i - 1][j - 1] = route.getCost();
        }

        List<Problem> problems = problemDao.getAllProblems();
        for (Problem problem : problems) {
            int id = problem.getId();
            int fromId = problem.getFromId();
            int toId = problem.getToId();
            if (fromId < toId)
                result = findMinPath((fromId - 1), (toId - 1), linkMatrix);
            else
                result = findMinPath((toId - 1), (fromId - 1), linkMatrix);
            solutionList.add(new Solution(id, result));
        }
        solutionDao.setSolution(solutionList);
    }
}