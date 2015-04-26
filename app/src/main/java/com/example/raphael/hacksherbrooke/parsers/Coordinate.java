package com.example.raphael.hacksherbrooke.parsers;

import java.io.Serializable;

public class Coordinate implements Serializable{
    public double latitude;
    public double longitude;
    public double altitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = 0;
    }

    public Coordinate(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Coordinate(Coordinate coord) {
        this.latitude = coord.latitude;
        this.longitude = coord.longitude;
        this.altitude = coord.altitude;
    }
}
