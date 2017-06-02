package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.serialization.SoapObject;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.person.impl.convert.SoapPersonConverter;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 6/2/2017.
 */

public class PersonProcessor extends NoArgsProcessor {
    private static final String METHOD_NAME = "persons";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/persons";
    private Collection<IPerson> persons = CollectionUtil.create();

    public PersonProcessor(String namespace, String url) {
        super(namespace, url);
    }

    @Override
    protected void createResult(SoapObject result) {
        persons.clear();
        for(int i= 0; i< result.getPropertyCount(); i++){
            SoapObject object = (SoapObject)result.getProperty(i);
            IPerson bean = new SoapPersonConverter().convert(object);
            persons.add(bean);
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
    public Collection<IPerson> result() {
        return persons;
    }
}
