package ru.rrozhkov.easykin.android.db.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rrozhkov on 5/17/2017.
 */

public class EasyKinDBHelper extends SQLiteOpenHelper {
    public EasyKinDBHelper(Context context) {
        super(context, "EasyKin", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table category ("
                + "id integer primary key,"
                + "name text)");
        db.execSQL("create table person ( "
                + "id integer primary key, "
                + "surname text, "
                + "name text, "
                + "secondname text, "
                + "sex text, "
                + "username text, "
                + "password text)");
        db.execSQL("create table task ( "
                + "id integer primary key, "
                + "name text, "
                + "priority integer, "
                + "category integer, "
                + "status integer)");
        db.execSQL("create table payment ( "
                + "id integer primary key, "
                + "comment text, "
                + "category integer, "
                + "status integer, "
                + "amount real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
