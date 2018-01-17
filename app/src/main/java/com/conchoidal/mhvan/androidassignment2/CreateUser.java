package com.conchoidal.mhvan.androidassignment2;

/**
 * Created by mhvan on 2017-12-20.
 */

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.conchoidal.mhvan.androidassignment2.MainActivity;
import com.conchoidal.mhvan.androidassignment2.R;
import com.conchoidal.mhvan.androidassignment2.matthew.DBHelper;
import com.conchoidal.mhvan.androidassignment2.matthew.User;


public class CreateUser extends AppCompatActivity {


    //private int userId;
    private static int nextUserId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String creditCard;
    private String address;
    private String postalCode;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Bundle extras = getIntent().getExtras();
        firstName = extras.getString("firstName");
        lastName = extras.getString("lastName");
        email = extras.getString("email");
        password = extras.getString("password");
        creditCard = extras.getString("creditCard");
        address = extras.getString("address");
        postalCode = extras.getString("postalCode");
        userName = extras.getString("userName");


    }


    public void submitClick(View v) {
        EditText etLName = (EditText) findViewById(R.id.editText_fName);
        EditText etFName = (EditText) findViewById(R.id.editText_lName);
        EditText etEmail = (EditText) findViewById(R.id.editText_email);
        EditText etPassword = (EditText) findViewById(R.id.editText_password);
        EditText etCreditCard = (EditText) findViewById(R.id.editText_cCard);
        EditText etAddress = (EditText) findViewById(R.id.editText_address);
        EditText etPostalCode = (EditText) findViewById(R.id.editText_pCode);
        EditText etUserName = (EditText) findViewById(R.id.editText_userName);

        String fName = etFName.getText().toString();
        String lName = etLName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String credit = etCreditCard.getText().toString();
        String address = etAddress.getText().toString();
        String postal = etPostalCode.getText().toString();
        String uName = etUserName.getText().toString();

        if (validation(fName, lName, email, password, credit, address, postal,uName)) {
// If validation was successful
            User user = new User(email,password,fName,lName,credit,address,postal,uName);


            DBHelper dbHelp = new DBHelper(getApplicationContext());
            SQLiteDatabase db = dbHelp.getReadableDatabase();

            dbHelp.insertUser(user, db);

            Intent i = new Intent(CreateUser.this, MainActivity.class);
            i.putExtra("firstname", fName);
            i.putExtra("lastname", lName);
          //  i.putExtra("fName", );
            i.putExtra("lName", lName);
          //  i.putExtra();
        //    i.putExtra("patientId", patient.getPatientId());

         //   Log.d("Debug", "1 : patientId = " + patient.getPatientId());
            startActivity(i);

        }
    }


    private boolean validation(String fName, String lName, String email, String password, String credit, String address, String postal, String userName) {
        //Validates the users input
        boolean check = false;
        //Regex to compare against
        String textRegex = "[a-zA-z]+([ '-][a-zA-Z]+)*"; //Used for names and department
        String creditRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$"; // Credit card regex
        String emailRegex ="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        String postalRegex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$"; // Used for Canadian Postal Codes




        if (!fName.matches(textRegex)) {
            Log.d("Validation", "fName.matches(textRegex) = " + fName.matches(textRegex));
            Toast.makeText(getApplicationContext(), "Invalid first name!" + textRegex, Toast.LENGTH_SHORT).show();
            return check;
        } else if (!lName.matches(textRegex)) {
            Toast.makeText(getApplicationContext(), "Invalid last name!", Toast.LENGTH_SHORT).show();
            return check;
        } else if (!fName.matches(textRegex)) {
            Toast.makeText(getApplicationContext(), "Invalid department!", Toast.LENGTH_SHORT).show();
            return check;
        } else if (!email.matches(emailRegex)) {
            Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
            return check;
        } else if (!credit.matches(creditRegex)) {
            Toast.makeText(getApplicationContext(), "Invalid room number!", Toast.LENGTH_SHORT).show();
            return check;
        } else if (!postal.matches(postalRegex)) {
            Toast.makeText(getApplicationContext(), "Invalid room number!", Toast.LENGTH_SHORT).show();
            return check;
        } else {
            //Validation is successful
            Log.d("Validation", "Validation successful");
            check = true;
            return check;
        }
    }


}
