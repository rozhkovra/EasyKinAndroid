package ru.rrozhkov.easykin.android.model.task.impl.convert;

import org.ksoap2.serialization.SoapObject;

import ru.rrozhkov.easykin.android.ws.client.util.DateUtil;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Priority;
import ru.rrozhkov.easykin.model.task.Status;


/**
 * Created by rrozhkov on 2/20/2017.
 */

public class TaskSoapConverter implements IConverter<ITask, SoapObject> {
    private String namespace;
    public TaskSoapConverter(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public SoapObject convert(ITask task) {
        SoapObject arg0 = new SoapObject(namespace, "");
        arg0.addProperty("category", task.getCategory().getId());
        arg0.addProperty("categoryName", task.getCategory().getName());
        arg0.addProperty("createDate", DateUtil.formatWs(task.getCreateDate()));
        arg0.addProperty("id", task.getId());
        arg0.addProperty("name", task.getName());
        arg0.addProperty("planDate", DateUtil.formatWs(task.getPlanDate()));
        arg0.addProperty("priority", Priority.priority(task.getPriority()));
        arg0.addProperty("status", Status.status(task.getStatus()));
        return arg0;
    }
}
