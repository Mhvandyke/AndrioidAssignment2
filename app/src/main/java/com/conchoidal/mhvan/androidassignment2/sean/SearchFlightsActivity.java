package com.conchoidal.mhvan.androidassignment2.sean;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.conchoidal.mhvan.androidassignment2.MainMenu;
import com.conchoidal.mhvan.androidassignment2.matthew.*;
import com.conchoidal.mhvan.androidassignment2.MainActivity;
import com.conchoidal.mhvan.androidassignment2.R;
import com.conchoidal.mhvan.androidassignment2.matthew.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFlightsActivity extends AppCompatActivity {
    int flightId;
    int userId;
    int flightNumberSend;
    //LIFECYCLE
    //##############################################################################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        //DATABASES
        DBHelper openHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase ass2DB = openHelper.getWritableDatabase();

        //INSTANTIATE BUTTONS
        Button searchFlights_btn = (Button) findViewById(R.id.search_flight_btn);
        Button back_btn = (Button) findViewById(R.id.back_btn);

        //INTENTS
        final Intent back_intent = new Intent(this, MainActivity.class);

        //SET BUTTON ON CLICKS
        //FLIGHTS SEARCH BUTTON
        searchFlights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataToNext();
            }
        });

        //BACK BUTTON LISTENER
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(back_intent);
            }
        });
    }

    //HELPERS
    //##############################################################################################
    //FIELD VALIDATION - CHECK FOR NULL AND REGEX
    private ArrayList<String> sendDataToNext() throws SQLiteException {

        //FIELDS
        ArrayList<String> payload = new ArrayList<String>(); // data object sent to activity

        //PAGE CONTROLS
        EditText department_field = (EditText) findViewById(R.id.depart_field); //reference to depo
        EditText origin_field = (EditText) findViewById(R.id.origin_field); // reference to origin
        EditText destination_field = (EditText) findViewById(R.id.destination_field);//ref to des
        TextView error = (TextView) findViewById(R.id.error_label); // ref to error label

        //DATABASE HOUSEKEEPING

        DBHelper openHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase ass2DB = openHelper.getWritableDatabase();


        try {
            String query = "SELECT * FROM table_flight WHERE departDate = '" + department_field.getText().toString() + "' " +
                    "OR origin = '" + origin_field.getText().toString() + "' " +
                    "OR  destination = '" + destination_field.getText().toString() + "'";
            Cursor cursor = ass2DB.rawQuery(query, null);

            //COLUMN_NAMES

            int flightNum = cursor.getColumnIndex("flightNum");
            int departDate = cursor.getColumnIndex("departDate");
            int departTime = cursor.getColumnIndex("departTime");
            int arriveDate = cursor.getColumnIndex("arriveDate");
            int arriveTime = cursor.getColumnIndex("arriveTime");
            int origin = cursor.getColumnIndex("origin");
            int destination = cursor.getColumnIndex("destination");
            int cost = cursor.getColumnIndex("cost");
            int travelTime = cursor.getColumnIndex("travelTime");
            //iterate over results
            cursor.moveToFirst();
            flightId = cursor.getColumnIndex("flightNum");
            while (!cursor.isAfterLast()) {

                payload.add(cursor.getString(flightNum));
                flightNumberSend = Integer.parseInt((cursor.getString(flightNum)));
                payload.add(cursor.getString(departDate));
                payload.add(cursor.getString(departTime));

                payload.add(cursor.getString(arriveDate));
                payload.add(cursor.getString(arriveTime));

                payload.add(cursor.getString(origin));
                payload.add(cursor.getString(destination));

                payload.add(cursor.getString(cost));
                payload.add(cursor.getString(travelTime));

                cursor.moveToNext();
            }

            Log.d("HI","STARTING PRINTOUT: ");
            printPayload(payload);
            //close cursor
            cursor.close();
            return payload;
        }
        //catch if null and redirect to page
        catch (SQLiteException e) {

            Log.d("Stack", "Going back to main page");
            final Intent search_again = new Intent(this, SearchFlightsActivity.class);
            e.printStackTrace();
            startActivity(search_again);
        }

        return payload;
    }

    private void printPayload(List<String> payload){
        for(int x = 0; x < payload.size(); x++) {

            //CHECK
            Log.d("msg", payload.get(x));

            //INSTANTIATE CONTROLS
            TextView flNum = (TextView) findViewById(R.id.flight_num_field);
            TextView depDay = (TextView) findViewById(R.id.depart_date_field);
            TextView depTim = (TextView) findViewById(R.id.depart_time_field);
            TextView arvD = (TextView) findViewById(R.id.arrive_date_field);
            TextView arvT = (TextView) findViewById(R.id.arrive_time_field);
            TextView org = (TextView) findViewById(R.id.origin_field_dis);
            TextView dst = (TextView) findViewById(R.id.destination_field_dis);
            TextView cst = (TextView) findViewById(R.id.cost_field);
            TextView trblT = (TextView) findViewById(R.id.travel_time_field);
            TextView err = (TextView) findViewById(R.id.error_label);


            //DISPLAY TO TABLE
            flNum.setText(payload.get(0));
            depDay.setText(payload.get(1));
            depTim.setText(payload.get(2));
            arvD.setText(payload.get(3));
            arvT.setText(payload.get(4));
            org.setText(payload.get(5));
            dst.setText(payload.get(6));
            cst.setText(payload.get(7));
            trblT.setText(payload.get(8));
        }
    }

    public void bookFlightBtn(View v) {
        Log.i("Test", "bookFlightBtn() called - num: " + flightNumberSend +" user id"+ userId);
        Intent i = new Intent(SearchFlightsActivity.this, BookFlight.class);
        i.putExtra("userId", userId);
        i.putExtra("flightNumber", flightNumberSend);
        startActivity(i);
    }

}
