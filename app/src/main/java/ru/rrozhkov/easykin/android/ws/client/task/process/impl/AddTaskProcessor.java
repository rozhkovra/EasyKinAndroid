package ru.rrozhkov.easykin.android.ws.client.task.process.impl;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskSoapConverter;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.ws.process.impl.Processor;

/**
 * Created by rrozhkov on 2/28/2017.
 */

public class AddTaskProcessor extends Processor {
    private static final String METHOD_NAME = "add";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/add";
    private ITask task;
    private Collection<Integer> ids = CollectionUtil.create();

    public AddTaskProcessor(ITask task, String namespace, String url) {
        super(namespace, url);
        this.task = task;
    }

    @Override
    public void process() {
        complete = false;
        ids.clear();
        ids.add(-1);
        SoapObject request = new SoapObject(namespace, METHOD_NAME);
        SoapObject arg0 = new TaskSoapConverter(namespace).convert(task);
        request.addProperty("arg0",arg0);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(url);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapObject result = (SoapObject)envelope.bodyIn;
            int id = Integer.valueOf(result.getProperty("return").toString());
            ids.clear();
            ids.add(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ids.clear();
            ids.add(-1);
        }
        complete = true;
    }

    @Override
    public Collection<Integer> result() {
        return ids;
    }
}
