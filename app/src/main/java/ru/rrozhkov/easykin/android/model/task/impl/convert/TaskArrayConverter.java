package ru.rrozhkov.easykin.android.model.task.impl.convert;

import java.util.Collection;

import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.task.ITask;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class TaskArrayConverter implements IConverter<Collection<ITask>, String[]> {
    @Override
    public String[] convert(Collection<ITask> tasks) {
        Collection<String> cats = CollectionUtil.create();
        for(ITask bean : tasks){
            cats.add(bean.getName());
        }
        return cats.toArray(new String[tasks.size()]);
    }
}
