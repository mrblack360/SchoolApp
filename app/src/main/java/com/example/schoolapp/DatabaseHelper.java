package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper (Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public addAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put(admin.admin_id, admin.getFirst_name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("staff", null, values);
        db.close();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table admin(admin_id varchar(6) primary key, first_name varchar(15), middle_name varchar(15), last_name varchar(15), gender varchar(6), birthdate datetime, email varchar(30), location_id int(8))");
    }

    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS admin");
        onCreate(db);
    }
}