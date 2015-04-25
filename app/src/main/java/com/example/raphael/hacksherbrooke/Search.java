package com.example.raphael.hacksherbrooke;

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
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Search extends ActionBarActivity {

    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<BikeRoad> possiblePaths = new ArrayList<>();
    ArrayList<CheckBox> CheckBoxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    public  void ButtomOnButtonClick(View v) {

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
            parameters.add(TerrainSelected.getText().toString());
            parameters.add(TimeSelected.getText().toString());
            parameters.add(DenivelationSelected.getText().toString());
            parameters.add(RideSelected.getText().toString());

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

    }

    public void Evaluate(ArrayList<BikeRoad> bikeRoadsArray){




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
