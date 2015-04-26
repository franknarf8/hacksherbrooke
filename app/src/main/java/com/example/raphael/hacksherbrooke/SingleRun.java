package com.example.raphael.hacksherbrooke;

import android.graphics.Point;
import android.os.Debug;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.raphael.hacksherbrooke.parsers.BikeRoadParser;
import com.example.raphael.hacksherbrooke.parsers.DataObjects.BikeRoad;
import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest.Type.*;

public class SingleRun extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private List<BikeRoad> paths;
    private List<PointOfInterest> points;
    private BikeRoad path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        paths = (List< BikeRoad>)getIntent().getSerializableExtra("Coordinate");
        points = (List<PointOfInterest>)getIntent().getSerializableExtra("Points");
        setContentView(R.layout.activity_single_run);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
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
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.smallMap))
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

        ArrayList<PolylineOptions> line = new ArrayList<>();

        for(int i = 0 ; i< paths.size(); i++){

            PolylineOptions temp = new PolylineOptions();

            for(int k = 0 ; k < paths.get(i).geometry.size();k++){

                temp.add(new LatLng(paths.get(i).geometry.get(k).longitude, paths.get(i).geometry.get(k).latitude));

            }

            line.add(temp);
        }

        for(int l = 0 ; l< line.size(); l++){

            mMap.addPolyline(line.get(l));

        }

        for(int z = 0 ; z< points.size();z++){

            PointOfInterest.Type type = points.get(z).type;

            switch(type){

                case BAR:{
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.beericon)));
                    break;

                }
                case MAGASIN:{
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.quikemart)));
                    break;


                }

                case FOOD:{

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger)));
                    break;


                }

                case EVENT:{

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.event)));
                    break;

                }

                case POOL:{

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.swimmingpool)));
                    break;


                }

                case PARK:{

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));
                    break;


                }

                default:{

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(points.get(z).location.latitude, points.get(z).location.longitude))
                            .title(points.get(z).name)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.question)));
                    break;

                }

            }

        }

    }
}
