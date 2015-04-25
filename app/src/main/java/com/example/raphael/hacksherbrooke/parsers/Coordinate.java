package com.example.raphael.hacksherbrooke.parsers;

public class Coordinate {
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
}
