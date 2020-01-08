package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper (Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase adminDB = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase adminDB){
        adminDB.execSQL("create table admin(admin_id text(6) primary key, first_name text(15), middle_name text(15), last_name text(15), gender text(6), birth_date datetime, email text(30), location_id int(8))");
    }

    public  void onUpgrade(SQLiteDatabase adminDB, int oldVersion, int newVersion){
        adminDB.execSQL("DROP TABLE IF EXISTS admin");

    }
    public void addAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put("admin_id", admin.admin_id);
        values.put("first_name", admin.first_name);
        values.put("middle_name", admin.middle_name);
        values.put("last_name", admin.last_name);
        values.put("gender", admin.gender);
        values.put("birth_date", admin.birth_date);
        values.put("location_id", admin.location_id);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("staff", null, values);
        db.close();
    }


}