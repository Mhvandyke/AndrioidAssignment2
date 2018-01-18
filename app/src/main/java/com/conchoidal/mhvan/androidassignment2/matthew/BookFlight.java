package com.conchoidal.mhvan.androidassignment2.matthew; //change to appropriate package

/**
 * Created by James on 12/20/17.
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.conchoidal.mhvan.androidassignment2.MainActivity;
import com.conchoidal.mhvan.androidassignment2.MainMenu;
import com.conchoidal.mhvan.androidassignment2.R;

public class BookFlight extends AppCompatActivity {
    DBHelper dbHelp;
    SQLiteDatabase db;
    int userId;
    int flightNumber;
    int flightId;
    String destination;
    String flightNum;
    String origin;
    String departDate;
    String departTime;
    String arriveDate;
    String arriveTime;
    String travelTime;
    String cost;
    BookedFlight bookedFlight;
    Cursor c;
    TextView flightNumText;
    TextView destinationText;
    TextView originText;
    TextView departDateText;
    TextView departTimeText;
    TextView arriveDateText;
    TextView arriveTimeText;
    TextView costText;
    TextView travelTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        flightNumber = extras.getInt("flightNumber");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);
        Log.d("BookFlight", "on create" + userId + " "+ flightNumber);

        dbHelp = new DBHelper(getApplicationContext());
        db = dbHelp.getReadableDatabase();
        dbHelp.onUpgrade(db, 1, 2);
        c = dbHelp.returnFlightRow(flightNumber, db);
        Log.d("BookFlight", "test = " + c);
        destination = c.getString(c.getColumnIndex("destination"));
        Log.d("BookFlight", "destination = " + destination);
        origin = c.getString(c.getColumnIndex("origin"));
        arriveDate = c.getString(c.getColumnIndex("arriveDate"));
        arriveTime = c.getString(c.getColumnIndex("arriveTime"));
        departDate = c.getString(c.getColumnIndex("departDate"));
        departTime = c.getString(c.getColumnIndex("departTime"));
        flightNum = c.getString(c.getColumnIndex("flightNum"));
        cost = c.getString(c.getColumnIndex("cost"));
        travelTime = c.getString(c.getColumnIndex("travelTime"));
        flightId = c.getInt(c.getColumnIndex("flightId"));


        flightNumText = (TextView)findViewById(R.id.flightNumText);
        destinationText = (TextView)findViewById(R.id.destinationText);
        originText = (TextView)findViewById(R.id.originText);
        departDateText = (TextView)findViewById(R.id.departDateText);
        departTimeText = (TextView)findViewById(R.id.departTimeText);
        arriveDateText = (TextView)findViewById(R.id.arriveDateText);
        arriveTimeText = (TextView)findViewById(R.id.arriveTimeText);
        costText = (TextView)findViewById(R.id.costText);
        travelTimeText = (TextView)findViewById(R.id.travelTimeText);

        flightNumText.setText("Flight Number: " + flightNum);
        destinationText.setText("Destination: " + destination);
        originText.setText("Origin: " + origin);
        departDateText.setText("Depart Date: " + departDate);
        departTimeText.setText("Depart Time: " + departTime);
        arriveDateText.setText("Arrive Date: " + arriveDate);
        arriveTimeText.setText("Arrive Time: " + arriveTime);
        travelTimeText.setText("Flight Time: " + travelTime);
        costText.setText("Price: $" + cost);

    }
    public void BookFlightButton(View view){
        bookedFlight = new BookedFlight(userId, flightId);
        dbHelp.insertBookedFlight(bookedFlight, db);
        Toast.makeText(this, "Flight #" + flightNum + " has been booked!", Toast.LENGTH_LONG).show();
    }
    public void BackButton(View view){
        Intent i = new Intent(BookFlight.this, MainMenu.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}
