package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper (Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table admin(admin_id varchar(6) primary key, password varchar(10), first_name varchar(15), middle_name varchar(15), last_name varchar(15), gender varchar(6), birth_date datetime, email varchar(30), location_id int(8))");
    }

    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS admin");
        onCreate(db);
        createDefaultAdmin();
    }
    public void addAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put("admin_id", admin.admin_id);
        values.put("password", admin.password);
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

    public boolean authenticateAdmin(String username, String password){
        final boolean a = true;
        final boolean b = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("admin",new String[]{"admin_id","password"}, "admin_id", new String[]{username},null,null,null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            String Admin_id = cursor.getString(0);
            String Password = cursor.getString(1);
            if (Admin_id==username && Password==password){
                return a;
            }
        }

        return b;
    }

    public void createDefaultAdmin(){
        Admin defaultAdmin = new Admin();
        defaultAdmin.Admin("AD0000", "admiN0", "Emmanuel", "Damas", "Emmanuel", "Male", "010119700000", "edamas@gmail.com", 273848);
        addAdmin(defaultAdmin);
    }

}