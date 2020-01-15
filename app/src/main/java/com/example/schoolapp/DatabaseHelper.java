package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.schoolapp.ui.addStudent.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    Location location = new Location();
    String createAdminTable = "create TABLE admin(admin_id TEXT PRIMARY KEY, first_name TEXT, middle_name VARCHAR(15), last_name VARCHAR(15), gender VARCHAR(6), birth_date datetime, email VARCHAR(30), location_id int(8))";
    String createUserTable = "CREATE TABLE user(user_name VARCHAR(10) PRIMARY KEY, password VARCHAR(10), user_role VARCHAR(10))";
    String createStudentTable = "CREATE TABLE student(student_id text(6) PRIMARY KEY, first_name TEXT, middle_name text(15), last_name text(15), gender VARCHAR(6), date_of_birth datetime, email VARCHAR(30), phone_number int(10), location_id int(8))";

    public DatabaseHelper (Context context){
        super(context, "School.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(createAdminTable);
        db.execSQL(createUserTable);
        db.execSQL(createStudentTable);
        db.execSQL(location.createRegionTable);
        db.execSQL(location.createDistrictTable);
        db.execSQL(location.createWardTable);
        db.execSQL(location.insertRegions);
        db.execSQL(location.insertDistricts);
        db.execSQL(location.insertWardsGroup1);
        db.execSQL(location.insertWardsGroup2);
    }

    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS admin");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("DROP TABLE IF EXISTS districts");
        db.execSQL("DROP TABLE IF EXISTS regions");
        db.execSQL("DROP TABLE IF EXISTS wards");
    }
    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("user_name", user.user_name);
        values.put("password", user.password);
        values.put("user_role", user.user_role);
        SQLiteDatabase userDB = this.getWritableDatabase();
        userDB.insert("user", null, values);
        userDB.close();
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

    public boolean authenticateUser(String user_name, String password){
        SQLiteDatabase userDB = this.getReadableDatabase();
        String selection = "user_name" + "=?";
        Cursor cursor = userDB.query("user", new String[]{"user_name", "password", "user_role"}, selection, new String[]{user_name}, null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
            String User_name = cursor.getString(0);
            String Password = cursor.getString(1);
            if (user_name.equals(User_name) && password.equals(Password)){
                return true;
            }
            return false;
        }else{
            Log.d("ELSE:", "authenticateUser: FAILED");
        }
        return false;

    }

    public boolean userExist(User user){
        String selection = "user_name" + "=?";
        SQLiteDatabase userDB = this.getReadableDatabase();
        Cursor cursor = userDB.query("user", new String[]{"user_name"}, selection, new String[]{user.user_name}, null, null, null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }

    public boolean studentExist(String student_id){
        SQLiteDatabase studentDB = this.getReadableDatabase();
        Cursor cursor = studentDB.query("student", new String[]{"student_id"}, "student_id", new String[]{student_id}, null,null,null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }

//    public Cursor getRegions(){
//        SQLiteDatabase regionDB = this.getWritableDatabase();
//        String query = "SELECT * FROM regions";
//        Cursor cursor = regionDB.rawQuery(query,null);
//        regionDB.close();
//        return cursor;
//    }

    public List<String> getRegions(){
        List<String> regionsList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM regions";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                regionsList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return regionsList;
    }

}