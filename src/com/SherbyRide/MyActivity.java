package com.SherbyRide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends Activity {
    private TextView message;
    private ImageView droid;

    private View.OnClickListener droidTapListener;

    int counter = 0;

    private void InitializeApp()
    {
        message = (TextView) findViewById(R.id.message);
        //droid = (ImageView) findViewById(R.id.imageView);

        // Define and attach listeners
        droidTapListener = new View.OnClickListener()  {
            public void onClick(View v) {
                //TapDroid();
                ++counter;
                message.setText(counter);
            }
        };
        droid.setOnClickListener(droidTapListener);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        InitializeApp();
    }
}
