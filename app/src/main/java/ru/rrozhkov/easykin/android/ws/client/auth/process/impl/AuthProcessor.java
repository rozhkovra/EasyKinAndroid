package ru.rrozhkov.easykin.android.ws.client.auth.process.impl;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Collection;

import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.core.ws.process.impl.Processor;


/**
 * Created by rrozhkov on 3/9/2017.
 */

public class AuthProcessor extends Processor {
    private static final String METHOD_NAME = "auth";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/auth";
    private String username;
    private String password;
    private Collection<Integer> ids = CollectionUtil.create();

    public AuthProcessor(String username, String password, String namespace, String url) {
        super(namespace, url);
        this.username = username;
        this.password = password;
        ids.clear();
        ids.add(-1);
    }

    @Override
    public void process() {
        complete = false;
        SoapObject request = new SoapObject(namespace, METHOD_NAME);
        request.addProperty("arg0",username);
        request.addProperty("arg1",password);

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
        }
        complete = true;
    }

    @Override
    public Collection<Integer> result() {
        return ids;
    }
}
