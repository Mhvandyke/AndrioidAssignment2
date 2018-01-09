package com.conchoidal.mhvan.androidassignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.conchoidal.mhvan.androidassignment2.sean.EditProfileActivity;
import com.conchoidal.mhvan.androidassignment2.sean.SearchFlightsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button that sends to flights page
        Button flights = (Button)findViewById(R.id.searchflight);
       final Intent flight_intent = new Intent(this, SearchFlightsActivity.class);

        //on click
        flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(flight_intent);
            }
        });


        //Button that sends to prpfile page
        Button profile = (Button)findViewById(R.id.editprofile);
        final Intent profile_intent = new Intent(this, EditProfileActivity.class);

        //on click
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profile_intent);
            }
        });



    }
}
