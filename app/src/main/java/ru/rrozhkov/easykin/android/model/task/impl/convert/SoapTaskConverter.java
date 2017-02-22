package ru.rrozhkov.easykin.android.model.task.impl.convert;

import org.ksoap2.serialization.SoapObject;

import java.util.Date;

import ru.rrozhkov.easykin.android.ws.client.util.DateUtil;
import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Priority;
import ru.rrozhkov.easykin.model.task.Status;
import ru.rrozhkov.easykin.model.task.impl.TaskFactory;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class SoapTaskConverter implements IConverter<SoapObject, ITask> {
    @Override
    public ITask convert(SoapObject object) {
        Date closeDate = null;
        try{
            closeDate = DateUtil.parseWs(object.getProperty("closeDate").toString());
        }catch(RuntimeException re){

        }
        ITask task =  TaskFactory.createTask(
                Integer.valueOf(object.getProperty("id").toString())
                , object.getProperty("name").toString()
                , DateUtil.parseWs(object.getProperty("createDate").toString())
                , DateUtil.parseWs(object.getProperty("planDate").toString())
                , Priority.priority(Integer.valueOf(object.getProperty("priority").toString()))
                , CategoryFactory.create(Integer.valueOf(object.getProperty("category").toString()), object.getProperty("categoryName").toString())
                , closeDate
                , Status.status(Integer.valueOf(object.getProperty("status").toString()))
        );
        return task;
    }
}
