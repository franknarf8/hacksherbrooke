package com.example.raphael.hacksherbrooke.parsers.DataObjects;

import com.example.raphael.hacksherbrooke.parsers.Coordinate;

public class PointOfInterest {
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
