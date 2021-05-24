package ua.com.alevel.dao;

import ua.com.alevel.model.Route;

import java.util.List;

public interface RouteDao {

    public void addRouteToDb (int [][] linkMatrix);
    public List<Route> getAllRoute();

}
