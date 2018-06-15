package ru.rrozhkov.easykin.android.model.task.impl.convert;

import android.database.Cursor;

import java.util.Date;

import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.impl.TaskFactory;
import ru.rrozhkov.easykin.core.convert.IConverter;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBTaskConverter implements IConverter<Cursor, ITask> {
    private static final TaskFactory taskFactory = TaskFactory.instance();
    @Override
    public ITask convert(Cursor cursor) {
        return taskFactory.createTask(cursor.getInt(cursor.getColumnIndex("id"))
                , cursor.getString(cursor.getColumnIndex("name"))
                , new Date()
                , new Date()
                , cursor.getInt(cursor.getColumnIndex("priority"))
                , cursor.getInt(cursor.getColumnIndex("category"))
                , cursor.getString(cursor.getColumnIndex("categoryName"))
                , new Date()
                , cursor.getInt(cursor.getColumnIndex("status")));
    }
}
