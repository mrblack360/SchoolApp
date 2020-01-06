package com.example.schoolapp.ui.addStudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    public StudentDatabaseHelper (Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase studentDB = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase studentDB){
        studentDB.execSQL("create table student(student_id varchar(6) primary key, first_name varchar(15), middle_name varchar(15), last_name varchar(15), gender varchar(6), birth_date datetime, email varchar(30), location_id int(8))");
    }

    public void onUpgrade(SQLiteDatabase studentDB, int oldVersion, int newVersion){
        studentDB.execSQL("DROP TABLE IF EXISTS student");
    }
}
