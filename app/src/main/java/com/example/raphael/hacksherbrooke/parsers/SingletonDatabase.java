package com.example.raphael.hacksherbrooke.parsers;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.BikeRoad;
import com.example.raphael.hacksherbrooke.parsers.DataObjects.District;
import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;

import java.util.List;

/**
 * Created by frank on 25/04/15.
 */
public class SingletonDatabase {
    private static SingletonDatabase instance = null;
    private SingletonDatabase(){}

    public static SingletonDatabase getInstance() {
        if (instance == null)
            instance = new SingletonDatabase();
        return instance;
    }

    public List<BikeRoad> bikeRoads;
    public List<PointOfInterest> ptsInterest;
    public List<District> districts;
}
