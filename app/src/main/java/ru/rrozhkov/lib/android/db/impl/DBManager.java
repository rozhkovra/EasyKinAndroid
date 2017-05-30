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
    private EasyKinDBHelper dbHelper;

    public DBManager(EasyKinDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public int nextId(String s) throws SQLException {
        return 0;
    }

    @Override
    public <T> Collection<T> select(String sql, IConverter<Cursor, T> iConverter) throws SQLException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Collection<T> collection = CollectionUtil.create();
        Cursor c = db.rawQuery(sql, new String[]{});
        if (c.moveToFirst()) {
            collection.add(iConverter.convert(c));
        }
        c.close();
        dbHelper.close();
        return collection;
    }

    @Override
    public int insert(String s, ContentValues map) throws SQLException {
        return 0;
    }

    @Override
    public int update(String s, ContentValues map) throws SQLException {
        return 0;
    }
}
