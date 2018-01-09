package com.conchoidal.mhvan.androidassignment2.matthew;
/**
 * Created by matt on 12/20/17.
 */

public class BookedFlight {
    private int bookedFlightId;
    private int userId;
    private int flightId;
    private static int nextBFId = 0;

    public BookedFlight() {
    }

    public BookedFlight(int userId, int flightId) {
        this.bookedFlightId = nextBFId;
        this.userId = userId;
        this.flightId = flightId;
        nextBFId++; //Auto-increment nextBFId every time a new user is created
    }

    public int getBookedFlightId() {
        return bookedFlightId;
    }

    public void setBookedFlightId(int bookedFlightId) {
        this.bookedFlightId = bookedFlightId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}