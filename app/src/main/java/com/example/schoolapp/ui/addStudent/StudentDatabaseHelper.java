package com.example.schoolapp.ui.addStudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    public StudentDatabaseHelper (Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase studentDB = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase studentDB){
        studentDB.execSQL("create table student(student_id varchar(6) primary key, first_name varchar(15), middle_name varchar(15), last_name varchar(15), gender varchar(6), date_of_birth datetime, email varchar(30), phone_number int(10), location_id int(8))");
    }

    public void onUpgrade(SQLiteDatabase studentDB, int oldVersion, int newVersion){
        studentDB.execSQL("DROP TABLE IF EXISTS student");
    }

    public void addStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("student_id", student.student_id);
        values.put("first_name", student.first_name);
        values.put("middle_name", student.middle_name);
        values.put("last_name", student.last_name);
        values.put("gender", student.gender);
        values.put("date_of_birth", student.date_of_birth);
        values.put("email", student.email);
        values.put("phone_no", student.phone_number);
        values.put("location_id", student.location_id);
        SQLiteDatabase studentDB = getWritableDatabase();
        studentDB.insert("student", null, values);
        studentDB.close();
    }
    public boolean studentExist(String student_id){
        SQLiteDatabase studentDB = this.getReadableDatabase();
        Cursor cursor = studentDB.query("student", new String[]{"student_id"}, "student_id", new String[]{student_id}, null,null,null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}
