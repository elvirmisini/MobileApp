package com.example.mobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "DataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Users(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT, Lastname TEXT, Email TEXT,PhoneNumber INTEGER, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Users");
        /*
        Log.w("TaskDBAdapter", "Upgrading from version " +oldVersion + " to "+newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");

        onCreate(db);

         */

    }

    public Boolean insertData(String Name, String Lastname, String Email , String PhoneNumber , String Password ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("LastName",Lastname);
        contentValues.put("Email",Email);
        contentValues.put("Phone",PhoneNumber);
        contentValues.put("Password",Password);
        long result=db.insert("Users",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }

    public Boolean checkemail(String Email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Users where Email = ?",new String[]{Email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkemailPassword(String Email,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Users where Email = ? and Password =?",new String[]{Email,Password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public boolean change(String Email , String NewPassword )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Update Users set Password=? where Email=?",new String[]{NewPassword,Email});

        if (cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
}

