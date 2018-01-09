package com.conchoidal.mhvan.androidassignment2.matthew;

/**
 * Created by matt on 12/19/17.
 */

public class Flight {
    private int flightId;
    private int flightNum;
    private String departDate; //Should be stored as YYYY-MM-DD
    private String departTime; //Should be stored as HH:MM not starting at 00:00 (12AM) and not exceeding 23:59 (11:59PM)
    private String arriveDate; //Should be stored as YYYY-MM-DD
    private String arriveTime; //Should be stored as HH:MM not starting at 00:00 (12AM) and not exceeding 23:59 (11:59PM)
    private String origin;
    private String destination;
    private double cost;
    private String travelTime; //Should be stored as HH:MM
    private static int nextFlightId = 0;
    public Flight(int flightNum, String departDate, String departTime, String arriveDate, String arriveTime, String origin, String destination,
                  double cost, String travelTime) {
        this.flightId = nextFlightId;
        this.flightNum = flightNum;
        this.departDate = departDate;
        this.departTime = departTime;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.travelTime = travelTime;
        nextFlightId++; //Auto-increment nextFlightId every time a new flight is created
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public static int getNextFlightId() {
        return nextFlightId;
    }

    public static void setNextFlightId(int nextFlightId) {
        Flight.nextFlightId = nextFlightId;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

}