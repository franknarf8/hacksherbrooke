package com.example.raphael.hacksherbrooke.parsers;

import java.io.Serializable;
import java.util.List;

public class BikeRoad implements Serializable{
    public String id;
    public String nomVille;
    public String nomDestinationSherbrooke;
    public String nomMTQ;
    public String remarque;
    public String largeur;
    public String type;
    public String pavement;
    public String typeMTQ1;
    public String typeMTQ2;
    public String sensorUsed;
    public Double lenght;
    public List<Coordinate> geometry;
}
