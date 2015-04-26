package com.example.raphael.hacksherbrooke.parsers.DataObjects;

import com.example.raphael.hacksherbrooke.parsers.Coordinate;

import java.io.Serializable;

public class PointOfInterest implements Serializable{
    public enum Type { FOOD, EVENT, ACTIVITY, POOL, MAGASIN, PARK, BAR, UNKNOWN }
    public Type type;
    public String name;
    public String phone;
    public Coordinate location;
    public String description;
    public String image;
    public String adresse;
    public String website;
}
