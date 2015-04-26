package com.example.raphael.hacksherbrooke;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Debug;
import android.util.Log;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.BikeRoad;
import com.example.raphael.hacksherbrooke.parsers.BikeRoadParser;
import com.example.raphael.hacksherbrooke.parsers.DataObjects.PointOfInterest;
import com.example.raphael.hacksherbrooke.parsers.FoodParser;
import com.example.raphael.hacksherbrooke.parsers.SingletonDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Search extends ActionBarActivity {

    ArrayList<String> parameters;
    ArrayList<BikeRoad> possiblePaths;
    ArrayList<CheckBox> CheckBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load datasets
        SingletonDatabase instance = SingletonDatabase.getInstance();
        try {
            AssetManager assetManager = getAssets();
            instance.ptsInterest = new FoodParser().getPointOfInterest(assetManager.open("restaurants.json"));
            instance.bikeRoads = new BikeRoadParser().getRoads(assetManager.open("pistecyclable.csv"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        parameters = new ArrayList<>();
        possiblePaths = new ArrayList<>();
        CheckBoxes = new ArrayList<>();

        setContentView(R.layout.activity_search);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    public  void ButtonOnButtonClick(View v) {

        Button button = (Button) v;

        //Fetch the necessary data if the button Submit is prerssed
        if(v.getId() == R.id.Submit){


            RadioGroup TerrainGroup=(RadioGroup)findViewById(R.id.radioGroupTerrain);
            RadioGroup TimeGroup=(RadioGroup)findViewById(R.id.radioGroupTime);
            RadioGroup DenivelationGroup=(RadioGroup)findViewById(R.id.radioGroupDenivelation);
            RadioGroup RideGroup=(RadioGroup)findViewById(R.id.radioGroupRideType);

            RadioButton TerrainSelected = (RadioButton)findViewById(TerrainGroup.getCheckedRadioButtonId());
            RadioButton TimeSelected = (RadioButton)findViewById(TimeGroup.getCheckedRadioButtonId());
            RadioButton DenivelationSelected = (RadioButton)findViewById(DenivelationGroup.getCheckedRadioButtonId());
            RadioButton RideSelected=(RadioButton)findViewById(RideGroup.getCheckedRadioButtonId());
            //Put the answers into strings Paved, Rocky, Extreme

            if(TerrainSelected == null){

                parameters.add("Paved");

            }else {

                parameters.add(TerrainSelected.getText().toString());

            }

            if(TimeSelected == null){

                parameters.add("30 to 45 min");

            }else {

                parameters.add(TimeSelected.getText().toString());

            }

            if(DenivelationSelected == null){

                parameters.add("Flat");

            }else{

                parameters.add(DenivelationSelected.getText().toString());

            }

            if(DenivelationSelected == null){

                parameters.add("Road Bike");

            }else{

                parameters.add(RideSelected.getText().toString());
            }


            CheckBox ch1 = (CheckBox)findViewById(R.id.checkBox1);
            CheckBox ch2 = (CheckBox)findViewById(R.id.checkBox2);
            CheckBox ch3 = (CheckBox)findViewById(R.id.checkBox3);
            CheckBox ch4 = (CheckBox)findViewById(R.id.checkBox4);
            CheckBox ch5 = (CheckBox)findViewById(R.id.checkBox5);
            CheckBox ch6 = (CheckBox)findViewById(R.id.checkBox6);
            CheckBox ch7 = (CheckBox)findViewById(R.id.checkBox7);
            CheckBox ch8 = (CheckBox)findViewById(R.id.checkBox8);
            CheckBox ch9 = (CheckBox)findViewById(R.id.checkBox9);

            CheckBoxes.add(ch1);
            CheckBoxes.add(ch2);
            CheckBoxes.add(ch3);
            CheckBoxes.add(ch4);
            CheckBoxes.add(ch5);
            CheckBoxes.add(ch6);
            CheckBoxes.add(ch7);
            CheckBoxes.add(ch8);
            CheckBoxes.add(ch9);

        }



        Evaluate(SingletonDatabase.getInstance().bikeRoads, SingletonDatabase.getInstance().ptsInterest);

    }

    public void Evaluate(List<BikeRoad> bikeRoadsArray, List<PointOfInterest> points){


        for(int i = 0; i< bikeRoadsArray.size();i++) {

            BikeRoad path = bikeRoadsArray.get(i);

            double denivelation = findDenivelation(path);

            //If we select asphalte and it is not
            if (parameters.get(0).equals("Paved") && !path.pavement.equals("Asphalte")) {

                continue;

            }

            //if we select a short length and the length of the path is long
            if (path.lenght >= 50 && parameters.get(1).equals("30 to 45 min")) {

                continue;

            }

            //Not 1-1h30 and path between 50 and 500
            if (path.lenght > 50 && path.lenght <= 500 && !parameters.get(1).equals("1h to 1h30 ")) {

                continue;

            }


            if (path.lenght > 500 && path.lenght <= 1000 && !parameters.get(1).equals("2h to 2h30")){

                continue;

            }

            if(path.lenght > 1000 && !parameters.get(1).equals("Whole afternoon")){

                continue;

            }

            if(denivelation < 25 && !parameters.get(2).equals("Flat")){

                continue;

            }

            if(denivelation < 100 && denivelation >= 25 && !parameters.get(2).equals("Hills")){

                continue;

            }

            if(denivelation >= 100 && !parameters.get(2).equals("Big Hills")){

                continue;

            }

            possiblePaths.add(path);


        }

        Intent nextMove = new Intent(this, SingleRun.class);

        nextMove.putExtra("Coordinate", possiblePaths);
        startActivity(nextMove);

    }


    public double findDenivelation(BikeRoad bikeRoadsArray){

        double sum = 0;

        for(int k = 1; k< bikeRoadsArray.geometry.size();k++){


            sum += abs(bikeRoadsArray.geometry.get(k).altitude - bikeRoadsArray.geometry.get(k).altitude);

        }

        sum = sum/bikeRoadsArray.lenght;

        return sum;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}