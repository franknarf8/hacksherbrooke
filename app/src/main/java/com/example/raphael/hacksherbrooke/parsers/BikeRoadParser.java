package com.example.raphael.hacksherbrooke.parsers;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BikeRoadParser {
    public List<BikeRoad> getRoads(InputStream stream) throws IOException {

        List<BikeRoad> liste = new ArrayList<>();

        try {
            InputStreamReader inputStream = new InputStreamReader(stream);

            CSVReader reader = new CSVReader(inputStream, '|');

            reader.readNext();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                CSVReader stringReader = new CSVReader(new StringReader(nextLine[12].substring(12, nextLine[12].length()-1)));
                BikeRoad road = new BikeRoad();
                road.id = nextLine[0];
                road.nomVille = nextLine[1];
                road.nomDestinationSherbrooke = nextLine[2];
                road.nomMTQ = nextLine[3];
                road.remarque = nextLine[4];
                road.largeur = nextLine[5];
                road.type = nextLine[6];
                road.pavement = nextLine[7];
                road.typeMTQ1 = nextLine[8];
                road.typeMTQ2 = nextLine[9];
                road.sensorUsed = nextLine[10];
                road.lenght = Double.parseDouble(nextLine[11].replace(',','.'));

                double altitude = 229.12819690817125;
                String[] coord;
                List<Coordinate> coords = new ArrayList<>();
                Random randomGenerator = new Random();
                while ((coord = stringReader.readNext()) != null) {
                    double coeff = randomGenerator.nextDouble();
                    for(int i = 0; i < coord.length; ++i, ++i) {
                        String[] parts = coord[i].split(" ");
                        coords.add((new Coordinate(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), altitude)));
                        altitude = coeff * randomGenerator.nextGaussian() * 54.6 * 0.1 + altitude;
                    }
                }
                road.geometry = coords;
                liste.add(road);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }
}
