package com.example.raphael.hacksherbrooke;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.raphael.hacksherbrooke.parsers.DataObjects.Profile;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void OnCoupleView(View v){

        Profile CoupleProfile = new Profile();

        CoupleProfile.profile = "Couple";
        CoupleProfile.DenivelationSelected = "Hills";
        CoupleProfile.RideSelected = "Road Bike";
        CoupleProfile.TerrainChoosen = "Paved";
        CoupleProfile.TimeSelected = "Whole afternoon";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",CoupleProfile);
        startActivity(i);
    }

    public  void OnMountainView(View v){

        Profile mountain = new Profile();
        mountain.profile = "Mountain";
        mountain.DenivelationSelected = "Big Hills";
        mountain.TerrainChoosen = "Rocky";
        mountain.RideSelected = "Mountain Bike";
        mountain.TimeSelected = "2h to 2h30";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",mountain);
        startActivity(i);
    }

    public void FunView(View v){

        Profile pro = new Profile();
        pro.profile = "Fun";
        pro.DenivelationSelected = "Hills";
        pro.TerrainChoosen = "Rocky";
        pro.RideSelected = "Kick Scooter";
        pro.TimeSelected = "1h to 1h30";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",pro);
        startActivity(i);
    }

    public void KidsView(View v){

        Profile pro = new Profile();
        pro.profile = "Kids";
        pro.DenivelationSelected = "Flat";
        pro.TerrainChoosen = "Paved";
        pro.RideSelected = "Rollerblades";
        pro.TimeSelected = "30 to 45 min";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",pro);
        startActivity(i);
    }

    public  void ExtremeMountain(View v){

        Profile pro = new Profile();
        pro.profile = "Extreme Mountain";
        pro.DenivelationSelected = "Extreme";
        pro.TerrainChoosen = "Rocky";
        pro.RideSelected = "Mountain Bike";
        pro.TimeSelected = "2h to 2h30";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",pro);
        startActivity(i);
    }

    public void RidingBike(View v){

        Profile pro = new Profile();
        pro.profile = "Riding";
        pro.DenivelationSelected = "Flat";
        pro.TerrainChoosen = "Paved";
        pro.RideSelected = "Road Bike";
        pro.TimeSelected = "1h to 1h30";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",pro);
        startActivity(i);

    }

    public void OnButtonPressed(View v){


        Profile pro = new Profile();
        pro.profile ="Custom";
        Intent i = new Intent(this, Search.class);
        i.putExtra("Profile",pro);
        startActivity(i);
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
