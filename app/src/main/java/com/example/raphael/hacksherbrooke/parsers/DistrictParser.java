package com.example.raphael.hacksherbrooke.parsers;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.District;
import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;
import com.example.raphael.hacksherbrooke.polygones.Point;
import com.example.raphael.hacksherbrooke.polygones.Polygon;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.raphael.hacksherbrooke.polygones.Polygon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank on 26/04/15.
 */
public class DistrictParser {
    public List<District> getDistricts(InputStream stream) {
        List<District> districts = new ArrayList<>();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject bigObj = new JSONObject(responseStrBuilder.toString());
            JSONArray features = bigObj.getJSONArray("features");
            for (int i = 0; i != features.length(); ++i) {
                JSONObject obj = features.getJSONObject(i);
                District district = new District();
                district.name = obj.getJSONObject("properties").getString("Nom du district");
                JSONArray pts = obj.getJSONObject("geometry").getJSONArray("coordinates").getJSONArray(0);
                Polygon.Builder builder = Polygon.Builder();
                for (int j = 0; j != pts.length(); ++j) {
                    JSONArray point = pts.getJSONArray(j);
                    builder.addVertex(new Point(point.getDouble(0), point.getDouble(1)));
                }
                district.poly = builder.build();

                districts.add(district);
            }
        } catch (Exception e) {
        }

        return districts;
    }
}