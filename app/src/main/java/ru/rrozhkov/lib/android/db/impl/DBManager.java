package ru.rrozhkov.lib.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.easykin.android.db.impl.EasyKinDBHelper;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.convert.IConverter;
import ru.rrozhkov.lib.db.IDBManager;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBManager implements IDBManager<Cursor, ContentValues> {
    protected EasyKinDBHelper dbHelper;
    private static String nextId = "SELECT MAX(ID)+1 AS ID FROM #table#";
    private static String deleteAll = "DELETE FROM #table#";

    public DBManager(EasyKinDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public int nextId(String tableName) throws SQLException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(nextId.replace("#table#", tableName), new String[]{});
        int id = 1;
        if (c.moveToFirst()) {
            id = c.getInt(c.getColumnIndex("ID"));
        }
        c.close();
        dbHelper.close();
        return id;
    }

    @Override
    public <T> Collection<T> select(String sql, IConverter<Cursor, T> iConverter) throws SQLException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Collection<T> collection = CollectionUtil.create();
        Cursor c = db.rawQuery(sql, new String[]{});
        if (c.moveToFirst()) {
            do {
                collection.add(iConverter.convert(c));
            }while(c.moveToNext());
        }
        c.close();
        dbHelper.close();
        return collection;
    }

    @Override
    public int insert(String tableName, ContentValues map) throws SQLException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int insertedRowId = (int)db.insert(tableName, null, map);
        dbHelper.close();
        return insertedRowId;
    }

    @Override
    public int update(String tableName, ContentValues map) throws SQLException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(tableName, map, null, null);
        dbHelper.close();
        return 1;
    }

    @Override
    public int deleteAll(String tableName) throws SQLException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName,null,null);
        dbHelper.close();
        return 1;
    }
}
