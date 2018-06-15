package ru.rrozhkov.easykin.android.model.task.impl.convert;

import java.util.Collection;

import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.task.ITask;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class TaskArrayStatusConverter implements IConverter<Collection<ITask>, String[]> {
    @Override
    public String[] convert(Collection<ITask> tasks) {
        Collection<String> cats = CollectionUtil.create();
        for(ITask bean : tasks){
            if(bean.getStatus().isClose())
                cats.add("["+bean.getStatus().toString()+"]["+bean.getCategory().getName()+"] "+bean.getName());
            else
                cats.add("["+bean.getCategory().getName()+"] "+bean.getName());
        }
        return cats.toArray(new String[tasks.size()]);
    }
}
