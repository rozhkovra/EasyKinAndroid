package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import ru.rrozhkov.easykin.android.model.task.impl.convert.DBTaskConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskContentConverter;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.android.db.impl.EntityHandler;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 6/1/2017.
 */

public class TaskHandler extends EntityHandler<ITask>{
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
