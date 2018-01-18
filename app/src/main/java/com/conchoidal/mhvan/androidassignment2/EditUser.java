package com.conchoidal.mhvan.androidassignment2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.conchoidal.mhvan.androidassignment2.matthew.DBHelper;
import com.conchoidal.mhvan.androidassignment2.matthew.User;

public class EditUser extends AppCompatActivity {

    int userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String creditCard;
    String address;
    String postalCode;
    String email;
    DBHelper dbHelp;
    SQLiteDatabase db;
    Cursor c;
    EditText editFName;
    EditText editLName;
    EditText editUName;
    EditText editPassword;
    EditText editCCard;
    EditText editAddress;
    EditText editPostal;
    EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        dbHelp = new DBHelper(getApplicationContext());
        db = dbHelp.getReadableDatabase();
        c = dbHelp.returnUserRow(userId, db);

        editFName = (EditText)findViewById(R.id.editText_fName);
        editLName = (EditText)findViewById(R.id.editText_lName);
        editUName = (EditText)findViewById(R.id.editText_userName);
        editPassword = (EditText)findViewById(R.id.editText_password);
        editCCard = (EditText)findViewById(R.id.editText_cCard);
        editAddress = (EditText)findViewById(R.id.editText_address);
        editPostal = (EditText)findViewById(R.id.editText_pCode);
        editEmail = (EditText)findViewById(R.id.editText_email);

        firstName = c.getString(c.getColumnIndex("firstName"));
        lastName = c.getString(c.getColumnIndex("lastName"));
        username = c.getString(c.getColumnIndex("username"));
        password = c.getString(c.getColumnIndex("password"));
        creditCard = c.getString(c.getColumnIndex("creditCard"));
        address = c.getString(c.getColumnIndex("address"));
        postalCode = c.getString(c.getColumnIndex("postalCode"));
        email = c.getString(c.getColumnIndex("email"));

        editFName.setText(firstName);
        editLName.setText(lastName);
        editUName.setText(username);
        editPassword.setText(password);
        editCCard.setText(creditCard);
        editAddress.setText(address);
        editPostal.setText(postalCode);
        editEmail.setText(email);
    }

    public void submitClick(View v) {
        //Add validation here!
        firstName = editFName.getText().toString();
        lastName = editLName.getText().toString();
        username = editUName.getText().toString();
        password = editPassword.getText().toString();
        creditCard = editCCard.getText().toString();
        address = editAddress.getText().toString();
        postalCode = editPostal.getText().toString();
        email = editEmail.getText().toString();

        User updatedUser = new User(userId, email, password, firstName, lastName, creditCard, address, postalCode, username);
        dbHelp.updateUser(updatedUser, db);
        Intent i = new Intent (EditUser.this, MainMenu.class);
        i.putExtra("userId", userId);
        Toast.makeText(getApplicationContext(), "User updated successfully!", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void backClick(View v) {
        Intent i = new Intent(EditUser.this, MainMenu.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}
