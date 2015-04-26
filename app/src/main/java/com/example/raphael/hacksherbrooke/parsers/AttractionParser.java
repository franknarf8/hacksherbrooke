package com.example.raphael.hacksherbrooke.parsers;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AttractionParser implements PointOfInterestParser {

    @Override
    public List<PointOfInterest> getPointOfInterest(InputStream stream) {
        List<PointOfInterest> pois = new ArrayList<>();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONArray array = new JSONArray(responseStrBuilder.toString());
            for (int i = 0; i != array.length(); ++i)
            {
                JSONObject obj = array.getJSONObject(i);
                PointOfInterest poi = new PointOfInterest();

                poi.adresse = obj.getString("NumeroCivique") + " "
                        + obj.getString("Rue") + " "
                        + obj.getString("Ville") + " "
                        + obj.getString("CodePostal");
                poi.name = obj.getString("Nom");
                poi.website = obj.getString("SiteWeb");
                poi.location = new Coordinate(obj.getDouble("Latitude"), obj.getDouble("Longitude"));
                poi.phone = obj.getString("NumeroTelephone");
                poi.description = obj.getString("DescriptionCourte");
                poi.image = obj.getString("FichierImage");
                String lowerName = poi.name.toLowerCase();
                String lowerDesc = poi.name.toLowerCase();
                if (lowerName.contains("bière") || lowerDesc.contains("bière") || lowerName.contains("bar") || lowerName.contains("brasseri") || lowerName.contains("pub") || lowerDesc.contains("bar "))
                    poi.type = PointOfInterest.Type.BAR;
                else if (lowerName.contains("parc "))
                    poi.type = PointOfInterest.Type.PARK;
                else if (lowerDesc.contains("boutique"))
                    poi.type = PointOfInterest.Type.MAGASIN;
                else
                    poi.type = PointOfInterest.Type.UNKNOWN;

                pois.add(poi);
            }
        } catch (Exception e){}

        return pois;
    }
}
