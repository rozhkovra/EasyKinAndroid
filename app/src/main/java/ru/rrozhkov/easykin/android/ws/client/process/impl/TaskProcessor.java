package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.serialization.SoapObject;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.task.impl.convert.SoapTaskConverter;
import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.model.task.ITask;


/**
 * Created by rrozhkov on 2/17/2017.
 */

public class TaskProcessor extends NoArgsProcessor{
    private static final String METHOD_NAME = "tasks";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/tasks";
    private Collection<ITask> tasks = CollectionUtil.create();

    public TaskProcessor(String namespace, String url) {
        super(namespace,url);
    }

    @Override
    protected void createResult(SoapObject result) {
        tasks.clear();
        for(int i= 0; i< result.getPropertyCount(); i++){
            SoapObject object = (SoapObject)result.getProperty(i);
            ITask bean = new SoapTaskConverter().convert(object);
            tasks.add(bean);
        }
    }

    @Override
    protected String methodName() {
        return METHOD_NAME;
    }

    @Override
    protected String soapAction() {
        return SOAP_ACTION;
    }

    @Override
    public Collection<ITask> result(){
        return tasks;
    }
}
