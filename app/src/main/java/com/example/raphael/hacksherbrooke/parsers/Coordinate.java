package com.example.raphael.hacksherbrooke.parsers;

import java.io.Serializable;

public class Coordinate implements Serializable{
    public double latitude;
    public double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
