package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.serialization.SoapObject;

import java.util.Collection;

import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class PingProcessor extends NoArgsProcessor{
    private static final String METHOD_NAME = "ping";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/ping";
    private Collection<Integer> values = CollectionUtil.create();

    public PingProcessor(String namespace, String url) {
        super(namespace, url);
        this.values.clear();
        this.values.add(-1);
    }

    @Override
    protected void createResult(SoapObject result) {
        int value = Integer.valueOf(result.getProperty("return").toString());
        this.values.clear();
        this.values.add(value);
    }

    @Override
    protected String methodName() {
        return METHOD_NAME;
    }

    @Override
    protected String soapAction() {
        return SOAP_ACTION;
    }

    public Collection<Integer> result(){
        return values;
    }
}
