package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.easykin.android.model.task.impl.convert.DBTaskConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskContentConverter;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.android.db.impl.EntityHandler;


/**
 * Created by rrozhkov on 6/1/2017.
 */

public class TaskHandler extends EntityHandler<ITask>{
    public String select = "SELECT "+tableName()+".*, category.name as categoryName FROM "+tableName()
            +" INNER JOIN category on "+tableName()+".category = category.id"
            +" ORDER BY status, priority, category";

    public Collection<ITask> select() throws SQLException {
        return EasyKinDBManager.instance().select(select, dbConverter());
    }
    @Override
    public String tableName() {
        return "task";
    }

    @Override
    public IConverter<ITask, ContentValues> converter() {
        return new TaskContentConverter();
    }

    @Override
    public IConverter<Cursor, ITask> dbConverter() {
        return new DBTaskConverter();
    }
}
