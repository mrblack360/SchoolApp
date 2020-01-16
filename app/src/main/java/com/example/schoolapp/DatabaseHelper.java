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
    String createAdminTable = "create TABLE admin(admin_id TEXT PRIMARY KEY, first_name TEXT, middle_name VARCHAR(15), last_name VARCHAR(15), gender VARCHAR(6), birth_date VARCHAR(13), email VARCHAR(30), location_id int(8))";
    String createUserTable = "CREATE TABLE user(user_name VARCHAR(10) PRIMARY KEY, password VARCHAR(10), user_role VARCHAR(10))";
    String createStudentTable = "CREATE TABLE student(student_id text(6) PRIMARY KEY, first_name TEXT, middle_name text(15), last_name text(15), gender VARCHAR(6), date_of_birth VARCHAR(13), email VARCHAR(30), phone_number int(10), location_id int(8))";

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
        values.put("phone_number", student.phone_number);
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

    public List<String> getDistricts(String regionName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "region_name=?";
        Cursor regionCode = db.query("regions", new String[]{"region_code",}, selection, new  String[] {regionName}, null,null, null);
        regionCode.moveToFirst();
        int region_code = regionCode.getInt(0);
        List<String> districtList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM districts WHERE region_code="+region_code;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                districtList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return districtList;
    }

    public List<String> getWards(String districtName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "district_name=?";
        Cursor regionCode = db.query("districts", new String[]{"district_code"}, selection, new  String[] {districtName}, null,null, null);
        regionCode.moveToFirst();
        int district_code = regionCode.getInt(0);
        List<String> districtList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM wards WHERE district_code="+district_code;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                districtList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return districtList;
    }

    public int wardCode(String wardName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "ward_name=?";
        Cursor wardCursor = db.query("wards", new String[]{"ward_code"}, selection, new  String[] {wardName}, null,null, null);
        wardCursor.moveToFirst();
        int ward_code = wardCursor.getInt(0);
        return  ward_code;
    }

    public String generateStudentId(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor studentCursor = db.rawQuery("SELECT * FROM student", null);
        int numberSuffix = studentCursor.getCount() + 10001;
        String regno = "2020-04-" + Integer.toString(numberSuffix);
        return  regno;
    }

    public List<String> StudentList() {
        List<String> studentList = new ArrayList<>();
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor c = db.rawQuery("SELECT * FROM student" , null);
//
//            if (c != null ) {
//                if  (c.moveToFirst()) {
//                    do {
//                        String student_id = c.getString(c.getColumnIndex("FirstName"));
//                        String first_name = c.getString(c.getColumnIndex("FirstName"));
//                        String middle_name = c.getString(c.getColumnIndex("FirstName"));
//                        String last_name = c.getString(c.getColumnIndex("FirstName"));
//                        studentList.add(student_id + "\t|\t " + first_name + "\t|\t " + middle_name + "\t|\t " + last_name);
//                    }while (c.moveToNext());
//                }
//            }

        return studentList;
    }
}