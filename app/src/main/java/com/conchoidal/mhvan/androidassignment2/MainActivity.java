package com.conchoidal.mhvan.androidassignment2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.conchoidal.mhvan.androidassignment2.matthew.DBHelper;


public class MainActivity extends AppCompatActivity {

    int userId;
    EditText editEmail;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
    }

    public void loginClick(View v) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        DBHelper dbHelp = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelp.getReadableDatabase();

        if(dbHelp.searchUserEmail(email, db)) {
            Cursor c = dbHelp.returnUserEmailRow(email, db);
            String tempPassword = c.getString(c.getColumnIndex("password"));
            if(password.equals(tempPassword)) {
                userId = c.getInt(c.getColumnIndex("userId"));
                nextScreen();
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Email not found!", Toast.LENGTH_SHORT).show();
        }

    }

    private void nextScreen(){
        Intent i = new Intent(MainActivity.this, MainMenu.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}
