package com.example.schoolapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public UserDatabaseHelper(Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase userDB = this.getReadableDatabase();
    }

    public void onCreate(SQLiteDatabase userDB){
        userDB.execSQL("CREATE table user(user_name varchar(6) primary key, password varchar(10), user_role varchar(10))");
    }

    public void onUpgrade(SQLiteDatabase userDB, int oldVersion, int newVersion){
        userDB.execSQL("DROP TABLE IF EXISTS user");
    }
}
