package ru.rrozhkov.lib.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.easykin.android.db.impl.EasyKinDBManager;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 6/1/2017.
 */

public abstract class EntityHandler<T> {
    public abstract String tableName();
    public abstract IConverter<T,ContentValues> converter();
    public abstract IConverter<Cursor,T> dbConverter();

    public String select = "SELECT * FROM "+tableName()+" ORDER BY ID";

    public Collection<T> select() throws SQLException {
        return EasyKinDBManager.instance().select(select, dbConverter());
    }

    public int insert(T t) throws SQLException{
        try {
            ContentValues map = converter().convert(t);
            int id = EasyKinDBManager.instance().nextId(tableName());
            map.put("id", id);
            return EasyKinDBManager.instance().insert(tableName(), map);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public int update(T t) throws SQLException{
        try {
            ContentValues map = converter().convert(t);
            return EasyKinDBManager.instance().update(tableName(), map);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void deleteAll() throws SQLException{
        try {
            EasyKinDBManager.instance().deleteAll(tableName());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
