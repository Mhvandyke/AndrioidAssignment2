package com.conchoidal.mhvan.androidassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.conchoidal.mhvan.androidassignment2.matthew.BookFlight;
import com.conchoidal.mhvan.androidassignment2.matthew.BookedFlight;
import com.conchoidal.mhvan.androidassignment2.matthew.Flight;
import com.conchoidal.mhvan.androidassignment2.matthew.User;
import com.conchoidal.mhvan.androidassignment2.sean.SearchFlightsActivity;


public class MainMenu extends AppCompatActivity {
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
    }

    public void viewFlightsBtn(View v) {
        Intent i = new Intent(MainMenu.this, SearchFlightsActivity.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

    public void createUserBtn(View v) {
        Intent i = new Intent(MainMenu.this, CreateUser.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
    public void editUserBtn(View v) {
        Intent i = new Intent(MainMenu.this, EditUser.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}