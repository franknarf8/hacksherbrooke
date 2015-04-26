package com.example.raphael.hacksherbrooke.parsers;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class WifiParser implements PointOfInterestParser {

    @Override
    public List<PointOfInterest> getPointOfInterest(InputStream stream) {
        List<PointOfInterest> liste = new ArrayList<>();

        try {
            InputStreamReader inputStream = new InputStreamReader(stream);

            CSVReader reader = new CSVReader(inputStream, ',', '"');

            reader.readNext();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                PointOfInterest poi = new PointOfInterest();
                poi.type = PointOfInterest.Type.WIFI;
                poi.name = nextLine[1];
                poi.description = nextLine[2];
                poi.adresse = nextLine[3] + " " + nextLine[4] + " " + nextLine[5] + " " + nextLine[8];
                poi.phone = nextLine[9];
                if (nextLine[11].isEmpty() || nextLine[12].isEmpty()) {
                    int a = 2;
                }
                poi.location = new Coordinate(Double.parseDouble(nextLine[11]), Double.parseDouble(nextLine[12]));

                liste.add(poi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }
}
