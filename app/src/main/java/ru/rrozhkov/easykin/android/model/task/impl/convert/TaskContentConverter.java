package ru.rrozhkov.easykin.android.model.task.impl.convert;

import android.content.ContentValues;

import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Priority;
import ru.rrozhkov.easykin.model.task.Status;


/**
 * Created by rrozhkov on 5/30/2017.
 */

public class TaskContentConverter implements IConverter<ITask, ContentValues> {
    @Override
    public ContentValues convert(ITask task) {
        ContentValues map = new ContentValues();
        map.put("id", task.getId());
        map.put("name", task.getName());
        map.put("category", task.getCategory().getId());
        map.put("priority", Priority.priority(task.getPriority()));
        map.put("status", Status.status(task.getStatus()));
        return map;
    }
}
