package ru.rrozhkov.easykin.android.db.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rrozhkov on 5/17/2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    public SQLiteDBHelper(Context context) {
        super(context, "EasyKin", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table category ("
        + "id integer primary key,"
        + "name text)';");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
