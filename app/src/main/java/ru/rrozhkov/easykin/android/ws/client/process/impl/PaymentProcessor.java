package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.payment.impl.convert.SoapPaymentConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.SoapTaskConverter;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class PaymentProcessor extends Processor {
    private static final String METHOD_NAME = "payments";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/payments";
    private Collection<IPayment> payments = CollectionUtil.create();

    public PaymentProcessor(String namespace, String url) {
        super(namespace,url);
    }

    @Override
    public void process(){
        complete = false;
        SoapObject request = new SoapObject(namespace, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(url);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapObject result = (SoapObject)envelope.bodyIn;
            payments.clear();
            for(int i= 0; i< result.getPropertyCount(); i++){
                SoapObject object = (SoapObject)result.getProperty(i);
                IPayment bean = new SoapPaymentConverter().convert(object);
                payments.add(bean);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        complete = true;
    }

    @Override
    public Collection<IPayment> result(){
        return payments;
    }
}
