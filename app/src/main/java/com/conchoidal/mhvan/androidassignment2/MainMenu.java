package com.conchoidal.mhvan.androidassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.conchoidal.mhvan.androidassignment2.matthew.Flight;
import com.conchoidal.mhvan.androidassignment2.matthew.User;


public class MainMenu extends AppCompatActivity {
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
    }

    private void ViewFlights() {
        Intent i = new Intent(MainMenu.this, Flight.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

    private void SearchFlights() {
        Intent i = new Intent(MainMenu.this, Flight.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

    private void CreateUser() {
        Intent i = new Intent(MainMenu.this, User.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
    private void EditUser() {
        Intent i = new Intent(MainMenu.this, User.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}