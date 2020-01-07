package com.example.schoolapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public UserDatabaseHelper(Context context){
        super(context, "School.db", null, 1);
        SQLiteDatabase userDB = this.getReadableDatabase();
    }

    public void onCreate(SQLiteDatabase userDB){
        userDB.execSQL("CREATE table user(user_name text(6), password text(10), user_role text(10))");
    }

    public void onUpgrade(SQLiteDatabase userDB, int oldVersion, int newVersion){
        userDB.execSQL("DROP TABLE IF EXISTS user");
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("user_name", user.user_name);
        values.put("password", user.password);
        values.put("user_role", user.user_role);
        SQLiteDatabase userDB = getWritableDatabase();
        userDB.insert("user", null, values);
        userDB.close();
    }

    public boolean userExist(String user_name){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.query("user", new String[]{"user_name"}, "user_name", new String[]{user_name}, null, null, null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}
