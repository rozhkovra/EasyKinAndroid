package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.lib.convert.IConverter;
import ru.rrozhkov.lib.db.IDBManager;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBManager implements IDBManager<Cursor, ContentValues> {
    @Override
    public int nextId(String s) throws SQLException {
        return 0;
    }

    @Override
    public <T> Collection<T> select(String s, IConverter<Cursor, T> iConverter) throws SQLException {
        return null;
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
