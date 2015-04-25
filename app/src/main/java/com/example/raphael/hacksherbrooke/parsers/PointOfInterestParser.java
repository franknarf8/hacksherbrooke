package com.example.raphael.hacksherbrooke.parsers;

import java.io.InputStream;
import java.util.List;

public interface PointOfInterestParser {
    List<PointOfInterest> getPointOfInterest(InputStream stream);
}
