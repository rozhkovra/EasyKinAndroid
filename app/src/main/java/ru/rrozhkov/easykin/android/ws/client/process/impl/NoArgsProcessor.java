package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import ru.rrozhkov.lib.ws.process.impl.Processor;

/**
 * Created by rrozhkov on 3/2/2017.
 */

public abstract class NoArgsProcessor extends Processor {
    public NoArgsProcessor(String namespace, String url) {
        super(namespace, url);
    }

    @Override
    public void process() {
        complete = false;
        SoapObject request = new SoapObject(namespace, methodName());

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(url);

        try {
            androidHttpTransport.call(soapAction(), envelope);

            createResult((SoapObject)envelope.bodyIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        complete = true;

    }

    protected abstract void createResult(SoapObject result);
    protected abstract String methodName();
    protected abstract String soapAction();
}
