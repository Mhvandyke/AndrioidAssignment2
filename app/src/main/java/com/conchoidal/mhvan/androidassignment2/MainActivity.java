package com.conchoidal.mhvan.androidassignment2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.conchoidal.mhvan.androidassignment2.matthew.DBHelper;


public class MainActivity extends AppCompatActivity {
    int userId;
    EditText editUsername;
    EditText editPassword;
    DBHelper dbHelp;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        dbHelp = new DBHelper(getApplicationContext());
        db = dbHelp.getReadableDatabase();
    }

    public void loginClick(View v) {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        DBHelper dbHelp = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Log.d("Database", "Database = " + db);
        Log.d("Database", "Username = " + username);
        Log.d("Database", "Password = " + password);

        if(dbHelp.searchUserUname(username, db)) {
            Log.d("Database", "Username exists. Checking password.");
            Cursor c = dbHelp.returnUserUnameRow(username, db);
            String tempPassword = c.getString(c.getColumnIndex("password"));
            Log.d("Database", "Comparing password.");
            if(password.equals(tempPassword)) {
                Log.d("Database", "Password match.");
                Log.d("Database", "User:" + username + "/Password:" + password + " has been verified!");
                userId = c.getInt(c.getColumnIndex("userId"));
                nextScreen();
            }
            else {
                Log.d("Database", "Password mismatch.");
                Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Username not found!", Toast.LENGTH_SHORT).show();
        }

    }

    private void nextScreen(){
        Intent i = new Intent(MainActivity.this, MainMenu.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

    public void createAccountBtn(View v){
        Intent i = new Intent(MainActivity.this, CreateUser.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}
