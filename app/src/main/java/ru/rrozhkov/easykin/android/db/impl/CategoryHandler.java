package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.easykin.android.model.category.impl.convert.CategoryContetntConverter;
import ru.rrozhkov.easykin.android.model.category.impl.convert.DBCategoryConverter;
import ru.rrozhkov.easykin.model.category.ICategory;

/**
 * Created by rrozhkov on 5/30/2017.
 */

public class CategoryHandler {
    private static String TABLENAME = "category";

    public static String select = "SELECT * FROM "+TABLENAME+" ORDER BY ID";

    public static String insert = "INSERT INTO "+TABLENAME
            +" (ID, NAME) "
            +" VALUES(#id#,'#name#')";

    public static String update = "UPDATE "+TABLENAME+" SET NAME='#name#' WHERE ID=#id#";

    public static Collection<ICategory> select() throws SQLException {
        return EasyKinDBManager.instance().select(select, new DBCategoryConverter());
    }

    public static int insert(ICategory category) throws SQLException{
        try {
            ContentValues map = new CategoryContetntConverter().convert(category);
            int id = EasyKinDBManager.instance().nextId(TABLENAME);
            map.put("id", id);
            EasyKinDBManager.instance().insert(TABLENAME, map);
            return id;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public static int update(ICategory category) throws SQLException{
        try {
            ContentValues map = new CategoryContetntConverter().convert(category);
            EasyKinDBManager.instance().update(update, map);
            return 1;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public static void deleteAll() throws SQLException{
        try {
            EasyKinDBManager.instance().deleteAll(TABLENAME);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
