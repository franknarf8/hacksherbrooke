package com.example.raphael.hacksherbrooke.parsers.DataObjects;

import android.widget.CheckBox;
import android.widget.RadioButton;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raphael on 2015-04-26.
 */
public class Profile implements Serializable{

    public String profile;
    public String TerrainChoosen;
    public String TimeSelected;
    public String DenivelationSelected;
    public String RideSelected;

    public List<CheckBox> checkboxes;


    };



