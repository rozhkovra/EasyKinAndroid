package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.category.impl.convert.SoapCategoryConverter;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.ws.process.impl.Processor;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class PingProcessor extends Processor {
    private static final String METHOD_NAME = "ping";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/ping";
    private Collection<Integer> values = CollectionUtil.create();

    public PingProcessor(String namespace, String url) {
        super(namespace, url);
    }

    public void process(){
        complete = false;
        this.values.clear();
        this.values.add(-1);
        SoapObject request = new SoapObject(namespace, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(url);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            int value = Integer.valueOf(result.getProperty("return").toString());
            this.values.clear();
            this.values.add(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.values.clear();
            this.values.add(-1);
        }
        complete = true;
    }

    public Collection<Integer> result(){
        return values;
    }
}
