package com.example.raphael.hacksherbrooke.parsers;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.BikeRoad;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

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
                    for(int i = 0; i != coord.length; ++i) {
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

        Collections.sort(liste, new RoadIdComparator());
        List<BikeRoad> finalList = new ArrayList<>();
        finalList.add(liste.get(0));
        for(int i = 1; i != liste.size(); ++i) {
            BikeRoad previous = finalList.get(finalList.size()-1);
            BikeRoad current = liste.get(i);
            if (/*current.nomMTQ.equals(previous.nomMTQ) &&
                    current.nomDestinationSherbrooke.equals(previous.nomDestinationSherbrooke) &&
                    current.nomVille.equals(previous.nomVille) &&*/
                    Math.abs(current.geometry.get(0).longitude - previous.geometry.get(previous.geometry.size()-1).longitude) < 0.0005
                    && Math.abs(current.geometry.get(0).latitude - previous.geometry.get(previous.geometry.size()-1).latitude) < 0.0005) {
                previous.geometry.addAll(current.geometry);
                previous.lenght += current.lenght;
            }
            else
                finalList.add(current);
        }

        //for (Iterator<BikeRoad> iter = finalList.listIterator(); iter.hasNext(); ) {
        //    if (iter.next().geometry.size() < 10) {
        //        iter.remove();
        //    }
        //}
        Collections.sort(finalList, new RoadLenghtComparator());
        int i = 0;
        while (i < finalList.size() && finalList.get(i).lenght > 700)
            ++i;

        finalList = finalList.subList(0, i);

        return finalList;
    }

    class RoadIdComparator implements Comparator<BikeRoad>{

        @Override
        public int compare(BikeRoad o1, BikeRoad o2) {
            return Integer.parseInt(o1.id) - Integer.parseInt(o2.id);
        }
    }

    class RoadLenghtComparator implements Comparator<BikeRoad>{

        @Override
        public int compare(BikeRoad o1, BikeRoad o2) {
            return (int) (o2.lenght - o1.lenght);
        }
    }
}
