package com.example.raphael.hacksherbrooke.parsers.DataObjects;

import com.example.raphael.hacksherbrooke.parsers.Coordinate;

import java.io.Serializable;

public class PointOfInterest implements Serializable{
<<<<<<< HEAD
    public enum Type { FOOD, EVENT, ACTIVITY, POOL, MAGASIN, PARK, BAR, UNKNOWN, WIFI }
=======
    public enum Type { FOOD, EVENT, ACTIVITY, POOL, MAGASIN, PARK, BAR, WIFI, UNKNOWN }
>>>>>>> 73961946c9d14cf169f111b2e10fc6f0d77962dd
    public Type type;
    public String name;
    public String phone;
    public Coordinate location;
    public String description;
    public String image;
    public String adresse;
    public String website;
}
