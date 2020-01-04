package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new DatabaseHelper(this);
    }

    public void login(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
