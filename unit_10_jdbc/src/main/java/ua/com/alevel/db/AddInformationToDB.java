package ua.com.alevel.db;

import ua.com.alevel.dao.impl.JdbcLocationDao;
import ua.com.alevel.dao.impl.JdbcProblemDao;
import ua.com.alevel.dao.impl.JdbcRouteDao;
import ua.com.alevel.model.Location;
import ua.com.alevel.model.Problem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AddInformationToDB {

    public void addDataToDbFromFile() {
        JdbcLocationDao locationDao = new JdbcLocationDao();
        JdbcRouteDao routeDao = new JdbcRouteDao();
        JdbcProblemDao problemDao = new JdbcProblemDao();
        List<String> citiesList = new ArrayList<>();
        try (InputStream input = AddInformationToDB.class.getResourceAsStream("/dataForDbAboutCities.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            int N = Integer.parseInt(reader.readLine());
            int[][] linkMatrix = new int[N][N];

            int countNeighbors;
            for (int i = 0; i < N; i++) {
                citiesList.add(reader.readLine());
                countNeighbors = Integer.parseInt(reader.readLine());
                for (int j = 0; j < countNeighbors; j++) {
                    StringTokenizer indexNeighborAndCost = new StringTokenizer(reader.readLine(), " ");
                    linkMatrix[i][Integer.parseInt(indexNeighborAndCost.nextToken()) - 1] = Integer.parseInt(indexNeighborAndCost.nextToken());
                }
            }
            List<Location> cities = addLocationToList(citiesList);
            locationDao.addLocationToDb(cities);
            routeDao.addRouteToDb(linkMatrix);

            int numberPathsToFind = Integer.parseInt(reader.readLine());
            StringBuilder paths = new StringBuilder();
            for (int i = 0; i < numberPathsToFind; i++)
                paths.append(reader.readLine()).append(",");

            StringTokenizer path = new StringTokenizer(paths.toString(), ",");
            for (int j = 0; j < numberPathsToFind; j++) {
                StringTokenizer fromTo = new StringTokenizer(path.nextToken(), " ");
                int indexFromCity = findIndexCity(fromTo.nextToken(), citiesList);
                int indexToCity = findIndexCity(fromTo.nextToken(), citiesList);

                problemDao.addProblemToDb(new Problem(j + 1, indexFromCity + 1, indexToCity + 1));
            }
        } catch (IOException e) {
            System.out.println("Data read error! Invalid file path");
        }
    }

    private List<Location> addLocationToList(List<String> citiesList) {
        List<Location> cities = new ArrayList<>();
        for (int i = 0; i < citiesList.size(); i++) {
            cities.add(new Location(i + 1, citiesList.get(i)));
        }
        return cities;
    }

    private int findIndexCity(String city, List<String> citiesList) {
        for (int i = 0; i < citiesList.size(); i++)
            if (citiesList.get(i).equals(city))
                return i;
        return 0;
    }
}


