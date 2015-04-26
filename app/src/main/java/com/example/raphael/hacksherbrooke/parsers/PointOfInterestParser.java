package com.example.raphael.hacksherbrooke.parsers;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;

import java.io.InputStream;
import java.util.List;

public interface PointOfInterestParser {
    List<PointOfInterest> getPointOfInterest(InputStream stream);
}
