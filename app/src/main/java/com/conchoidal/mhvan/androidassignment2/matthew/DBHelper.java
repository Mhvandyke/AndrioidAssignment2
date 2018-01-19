package com.conchoidal.mhvan.androidassignment2.matthew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Matthew on 2017-12-06.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "flight.db";

    public static final int DATABASE_VERSION = 6;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Flight table + create statement
    public static final String TABLE_FLIGHT = "table_flight";
    public static final String FLIGHTID = "flightId";
    public static final String FLIGHTNUM = "flightNum";
    public static final String DEPARTDATE = "departDate";
    public static final String DEPARTTIME = "departTime";
    public static final String ARRIVEDATE = "arriveDate";
    public static final String ARRIVETIME = "arriveTime";
    public static final String ORIGIN = "origin";
    public static final String DESTINATION = "destination";
    public static final String COST = "cost";
    public static final String TRAVELTIME = "travelTime";

    private static final String CREATE_TABLE_FLIGHT = "CREATE TABLE " +
            TABLE_FLIGHT + "(" +
            FLIGHTID + " INTEGER PRIMARY KEY," +
            FLIGHTNUM + " INTEGER," +
            DEPARTDATE + " TEXT," +
            DEPARTTIME + " TEXT," +
            ARRIVEDATE + " TEXT," +
            ARRIVETIME + " TEXT," +
            ORIGIN  + " TEXT," +
            DESTINATION + " TEXT," +
            COST  + " REAL," +
            TRAVELTIME + " TEXT" +
            ")";

    //User table + create statement
    public static final String TABLE_USER = "table_user";
    public static final String USERID = "userId";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String CREDITCARD = "creditCard";
    public static final String ADDRESS = "address";
    public static final String POSTALCODE = "postalCode";
    public static final String USERNAME = "username";



    private static final String CREATE_TABLE_USER = "CREATE TABLE " +
            TABLE_USER + "(" +
            USERID + " INTEGER PRIMARY KEY," +
            EMAIL + " TEXT," +
            PASSWORD + " TEXT," +
            FIRSTNAME + " TEXT," +
            LASTNAME + " TEXT," +
            CREDITCARD + " TEXT," +
            ADDRESS + " TEXT," +
            POSTALCODE + " TEXT," +
            USERNAME + " TEXT" +
            ")";

    public static final String TABLE_BOOKEDFLIGHT = "table_bookedflight";
    public static final String BOOKEDFLIGHTID = "bookedFlightId";

    private static String CREATE_TABLE_BOOKEDFLIGHT = "CREATE TABLE " +
            TABLE_BOOKEDFLIGHT + "(" +
            BOOKEDFLIGHTID + "INTEGER PRIMARY KEY," +
            USERID + " INTEGER," +
            FLIGHTID + " INTEGER" +
            ")";

    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "Running statement: " + CREATE_TABLE_FLIGHT);
        db.execSQL(CREATE_TABLE_FLIGHT);
        Log.d("Database", "Running statement: " + CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER);
        Log.d("Database", "Running statement: " + CREATE_TABLE_BOOKEDFLIGHT);
        db.execSQL(CREATE_TABLE_BOOKEDFLIGHT);



        Log.d("Database", "Tables created!");
        //Create flights
        Log.d("Database", "Creating flights");
        Flight flight1 = new Flight(23791, "2017-12-29", "11:30", "2017-12-29", "13:00", "Toronto", "Montreal", 200.0, "01:30");
        Flight flight2 = new Flight(80978, "2017-12-24", "12:00", "2017-12-25", "02:53", "Toronto", "Shanghai", 700.00, "14:53");
        Flight flight3 = new Flight(82763, "2018-01-26", "13:20", "2018-01-26", "01:45", "Vancouver", "Bejing", 650.00, "11:25");
        Flight flight4 = new Flight(74636, "2018-01-14", "06:24", "2018-01-15",  "12:30", "Edmonton", "Dallas", 300.00, "05:56");
        Flight flight5 = new Flight(54628, "2018-01-13", "08:39", "2018-01-13", "20:51", "Montreal", "Ibiza", 620.00, "12:12");
        insertFlight(flight1, db);
        insertFlight(flight2, db);
        insertFlight(flight3, db);
        insertFlight(flight4, db);
        insertFlight(flight5, db);

        //Create users
        Log.d("Database", "Creating users");
        User user1 = new User("admin@adminmail.com", "admin", "Admin", "Nimda", "1234567890123456", "123 Fake St.", "A1A-1A1", "admin");
        User user2 = new User("rick.sanchez@gmail.com", "morty", "Rick", "Sanchez", "3127345689074326", "64 Washington Lane" , "M3P-9A6", "rsanchez");
        User user3 = new User("mccarm416@gmail.com", "password", "Matthew" , "McCarthy" ,"5150342001763654", "66 Kendrick Blvd.", "G0P-6A9", "matthew");
        insertUser(user1, db);
        insertUser(user2, db);
        insertUser(user3, db);

        Log.d("Database", "Creating booked flights");
        BookedFlight bookedFlight1 = new BookedFlight(user1.getUserId(), flight2.getFlightId());
        BookedFlight bookedFlight2 = new BookedFlight(user2.getUserId(), flight4.getFlightId());
        insertBookedFlight(bookedFlight1, db);
        insertBookedFlight(bookedFlight2, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers) {
        Log.d("Database", "Dropping tables if they exist");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKEDFLIGHT);
        onCreate(db);
    }

    public void insertUser (User user, SQLiteDatabase db ) {
        Log.d("Database", "Preparing user " + user.getFirstName() + " to enter database.");
        ContentValues userValues = new ContentValues();
        userValues.put("userId", user.getUserId());
        userValues.put("email", user.getEmail());
        userValues.put("password", user.getPassword());
        userValues.put("firstName", user.getFirstName());
        userValues.put("lastName", user.getLastName());
        userValues.put("creditCard", user.getCreditCard());
        userValues.put("address", user.getAddress());
        userValues.put("postalCode", user.getPostalCode());
        userValues.put("username", user.getUsername());
        Log.d("Database", "Inserting user...");
        db.insert(TABLE_USER, null, userValues);
        Log.d("Database", "User " + user.getFirstName() + " has been inserted to the database.");
    }

    public void insertFlight (Flight flight, SQLiteDatabase db ) {
        Log.d("Database", "Entering flight  " + flight.getFlightNum() + " to database");
        ContentValues flightValues = new ContentValues();
        flightValues.put("flightId", flight.getFlightId());
        flightValues.put("flightNum", flight.getFlightNum());
        flightValues.put("departDate", flight.getDepartDate());
        flightValues.put("departTime", flight.getDepartTime());
        flightValues.put("arriveDate", flight.getArriveDate());
        flightValues.put("arriveTime", flight.getArriveTime());
        flightValues.put("origin", flight.getOrigin());
        flightValues.put("destination", flight.getDestination());
        flightValues.put("cost", flight.getCost());
        flightValues.put("travelTime", flight.getTravelTime());
        db.insert(TABLE_FLIGHT, null, flightValues);
    }

    public void insertBookedFlight(BookedFlight bookedFlight, SQLiteDatabase db) {
        Log.d("Database", "Entering booked flight for user - " + bookedFlight.getUserId() +
                " and flight - " + bookedFlight.getFlightId());
        ContentValues bookedFlightValues = new ContentValues();
        bookedFlightValues.put("userId", bookedFlight.getUserId());
        bookedFlightValues.put("flightId", bookedFlight.getFlightId());
    }
    public boolean searchFlight (int flightId, SQLiteDatabase db) {
        boolean isVaild = false;
        //Search for flight
        String selectQuery = "SELECT * FROM table_flight WHERE flightId = " + flightId ;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            isVaild = true;
        }

        Log.d("Database", "Flight cursor count = " + c.getCount() + ". isValid = " + isVaild + ".");
        return isVaild;
    }

    public boolean searchUser (int userId, SQLiteDatabase db) {
        boolean isVaild = false;
        //Search for user
        String selectQuery = "SELECT * FROM table_user WHERE userId = " + userId ;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            isVaild = true;
        }

        Log.d("Database", "User cursor count = " + c.getCount() + ". isValid = " + isVaild + ".");
        return isVaild;
    }

    public boolean searchUserUname (String username, SQLiteDatabase db) {
        Log.d("Database", "Searching username...");
        boolean isVaild = false;
        //Search for user
        Log.d("Database", "Running statement: \"SELECT * FROM table_user WHERE username = \"" + username + "\"");
        String selectQuery = "SELECT * FROM table_user WHERE username = \"" + username + "\"";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            isVaild = true;
        }
        Log.d("Database", "User.username cursor count = " + c.getCount() + ". isValid = " + isVaild + ".");
        return isVaild;
    }

    public boolean searchBookedFlight (int bookedFlightId, SQLiteDatabase db) {
        boolean isVaild = false;
        //Search for user
        String selectQuery = "SELECT * FROM table_bookedflight WHERE userId = " + bookedFlightId ;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            isVaild = true;
        }

        Log.d("Database", "Booked flight cursor count = " + c.getCount() + ". isValid = " + isVaild + ".");
        return isVaild;
    }

    public Cursor returnFlightRow(int flightNumber, SQLiteDatabase db) {
        String selectQuery = "SELECT * FROM table_flight WHERE flightNum = " + flightNumber;
        Cursor c;
        c = db.rawQuery(selectQuery, null);
        Log.d("Database", "1 : Cursor index = " + c);
        if (c == null) {
            Log.d("Database", "2 : Cursor == null!");

        }
        c.moveToFirst();
        Log.d("Database", "3 : Cursor index = " + c);
        return c;
    }

    public Cursor returnUserRow(int userId, SQLiteDatabase db) {
        String selectQuery = "SELECT * FROM table_user WHERE userId = " + userId ;
        Cursor c;
        c = db.rawQuery(selectQuery, null);
        Log.d("Database", "1 : Cursor index = " + c);
        if (c == null) {
            Log.d("Database", "2 : Cursor == null!");
        }
        c.moveToFirst();
        Log.d("Database", "3 : Cursor index = " + c);
        return c;
    }

    public Cursor returnUserUnameRow(String username, SQLiteDatabase db) {
        String selectQuery = "SELECT * FROM table_user WHERE username = \"" + username + "\"";
        Cursor c;
        c = db.rawQuery(selectQuery, null);
        Log.d("Database", "1 : Cursor index = " + c);
        if (c == null) {
            Log.d("Database", "2 : Cursor == null!");
        }
        c.moveToFirst();
        Log.d("Database", "3 : Cursor index = " + c);
        return c;
    }


    //UPDATE COMPANY SET ADDRESS = 'Texas' WHERE ID = 6;

    public void updateUser (User user, SQLiteDatabase db ) {
        Log.d("Database", "Updating user " + user.getFirstName() + " to enter database.");
        ContentValues userValues = new ContentValues();
        int userId = user.getUserId();
        String email = user.getEmail();
        String password =  user.getPassword();
        String firstName = user.getFirstName();
        String lastName =  user.getLastName();
        String creditCard = user.getCreditCard();
        String address =  user.getAddress();
        String postalCode = user.getPostalCode();
        String username = user.getUsername();
        String UPDATEUSER =
                "UPDATE table_user SET " +
                "email = \"" + email + "\", " +
                "password = \"" + password + "\", " +
                "firstName = \"" + firstName + "\", " +
                "lastName = \"" + lastName + "\", " +
                "creditCard = \"" + creditCard + "\", " +
                "address = \"" +  address + "\", " +
                "postalCode = \"" + postalCode + "\", " +
                "username = \"" + username +
                "\" WHERE userId = " + userId + ";";
        Log.d("Database", "Inserting user...");
        Log.d("Database", "Running statement: " + UPDATEUSER);
        db.execSQL(UPDATEUSER);
        Log.d("Database", "User " + user.getFirstName() + " has been updated in the database.");
    }

}
