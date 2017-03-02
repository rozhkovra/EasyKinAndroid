package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.serialization.SoapObject;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.payment.impl.convert.SoapPaymentConverter;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class PaymentProcessor extends NoArgsProcessor {
    private static final String METHOD_NAME = "payments";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/payments";
    private Collection<IPayment> payments = CollectionUtil.create();

    public PaymentProcessor(String namespace, String url) {
        super(namespace,url);
    }

    @Override
    protected void createResult(SoapObject result) {
        payments.clear();
        for(int i= 0; i< result.getPropertyCount(); i++){
            SoapObject object = (SoapObject)result.getProperty(i);
            IPayment bean = new SoapPaymentConverter().convert(object);
            payments.add(bean);
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
    public Collection<IPayment> result(){
        return payments;
    }
}
