package com.conchoidal.mhvan.androidassignment2.matthew;

/**
 * Created by matt on 12/19/17.
 */

public class User {
    private int userId;
    private static int nextUserId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String creditCard;
    private String address;
    private String postalCode;
    private String username;
    public User() {
    }

    public User(String email, String password, String firstName, String lastName, String creditCard, String address, String postalCode, String username) {
        this.userId = nextUserId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCard = creditCard;
        this.address = address;
        this.postalCode = postalCode;
        this.username = username;
        nextUserId++; //Auto-increment nextUserId every time a new user is created
    }

    public User(int userId, String email, String password, String firstName, String lastName, String creditCard, String address, String postalCode, String username) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCard = creditCard;
        this.address = address;
        this.postalCode = postalCode;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}