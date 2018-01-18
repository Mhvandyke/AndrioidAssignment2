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

import com.conchoidal.mhvan.androidassignment2.MainActivity;
import com.conchoidal.mhvan.androidassignment2.MainMenu;
import com.conchoidal.mhvan.androidassignment2.R;

public class BookFlight extends AppCompatActivity {
    DBHelper dbHelp;
    SQLiteDatabase db;
    int userId;
    int flightId;
    String destination;
    int flightNum;
    String origin;
    String departDate;
    String departTime;
    String arriveDate;
    String arriveTime;
    String travelTime;
    Double cost;
    BookedFlight bookedFlight;
    TextView flightNumText = (TextView)findViewById(R.id.flightNumText);
    TextView destinationText = (TextView)findViewById(R.id.destinationText);
    TextView originText = (TextView)findViewById(R.id.originText);
    TextView departDateText = (TextView)findViewById(R.id.departDateText);
    TextView departTimeText = (TextView)findViewById(R.id.departTimeText);
    TextView arriveDateText = (TextView)findViewById(R.id.arriveDateText);
    TextView arriveTimeText = (TextView)findViewById(R.id.arriveTimeText);
    TextView costText = (TextView)findViewById(R.id.costText);
    TextView travelTimeText = (TextView)findViewById(R.id.travelTimeText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);
        getLastPageInfo();
        getVariablesFromDB();
        setTextFields();

    }
    public void getLastPageInfo(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("userId");
            flightId = extras.getInt("flightId");
            bookedFlight = new BookedFlight(userId, flightId);
        }
    }

    public void getVariablesFromDB(){
        db = dbHelp.getReadableDatabase();
        Cursor c = dbHelp.returnFlightRow(flightId, db);
        dbHelp.onUpgrade(db, 1, 2);
        destination = c.getString(c.getColumnIndex("destination"));
        origin = c.getString(c.getColumnIndex("origin"));
        arriveDate = c.getString(c.getColumnIndex("arriveDate"));
        arriveTime = c.getString(c.getColumnIndex("arriveTime"));
        departDate = c.getString(c.getColumnIndex("departDate"));
        departTime = c.getString(c.getColumnIndex("departTime"));
        flightNum = c.getInt(c.getColumnIndex("flightNum"));
        cost = c.getDouble(c.getColumnIndex("cost"));
        travelTime = c.getString(c.getColumnIndex("travelTime"));
    }

    public void setTextFields(){
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
        dbHelp.insertBookedFlight(bookedFlight, db);
        Toast.makeText(this, "Flight #" + flightNum + " has been booked!", Toast.LENGTH_LONG).show();
    }
    public void BackButton(View view){
        Intent i = new Intent(BookFlight.this, MainMenu.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}
