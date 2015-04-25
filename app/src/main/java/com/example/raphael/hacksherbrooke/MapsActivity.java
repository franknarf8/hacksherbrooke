package com.example.raphael.hacksherbrooke;

import android.app.Activity;
import android.content.res.AssetManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    public List<List<Coordinate>> getRoutes(){
        List<List<Coordinate>> liste = new ArrayList<>();

        CSVReader reader = null;
        try {
            AssetManager assetManager = getAssets();
            InputStreamReader inputStream = new InputStreamReader(assetManager.open("pistecyclable.csv"));

            reader = new CSVReader(inputStream, '|');

            reader.readNext();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                CSVReader stringReader = new CSVReader(new StringReader(nextLine[12].substring(12, nextLine[12].length()-1)));
                String[] coord;
                List<Coordinate> coords = new ArrayList<>();
                while ((coord = stringReader.readNext()) != null) {
                    for(int i = 0; i < coord.length; ++i, ++i) {
                        String[] parts = coord[i].split(" ");
                        coords.add((new Coordinate(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]))));
                    }
                }
                liste.add(coords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        getRoutes();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
