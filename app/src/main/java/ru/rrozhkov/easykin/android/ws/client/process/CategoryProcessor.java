package ru.rrozhkov.easykin.android.ws.client.process;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.category.impl.convert.SoapCategoryConverter;
import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class CategoryProcessor {
    private static final String METHOD_NAME = "categories";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/categories";
    private String namespace;
    private String url;
    private Collection<ICategory> categories = CollectionUtil.create();
    private boolean complete = false;

    public CategoryProcessor(String namespace, String url) {
        this.namespace = namespace;
        this.url = url;
    }

    public void process(){
        complete = false;
        SoapObject request = new SoapObject(namespace, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(url);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapObject result = (SoapObject)envelope.bodyIn;
            categories.clear();
            for(int i= 0; i< result.getPropertyCount(); i++){
                SoapObject object = (SoapObject)result.getProperty(i);
                ICategory bean = new SoapCategoryConverter().convert(object);
                categories.add(bean);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public Collection<ICategory> result(){
        return categories;
    }

    public void setComplete(boolean complete){
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }
}
