package ru.rrozhkov.easykin.android.ws.client.process.impl;

import org.ksoap2.serialization.SoapObject;

import java.util.Collection;

import ru.rrozhkov.easykin.android.model.category.impl.convert.SoapCategoryConverter;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class CategoryProcessor extends NoArgsProcessor {
    private static final String METHOD_NAME = "categories";
    private static final String SOAP_ACTION = "http://rrozhkov.ru/easykin/categories";
    private Collection<ICategory> categories = CollectionUtil.create();

    public CategoryProcessor(String namespace, String url) {
        super(namespace, url);
    }

    @Override
    protected void createResult(SoapObject result) {
        categories.clear();
        for(int i= 0; i< result.getPropertyCount(); i++){
            SoapObject object = (SoapObject)result.getProperty(i);
            ICategory bean = new SoapCategoryConverter().convert(object);
            categories.add(bean);
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

    public Collection<ICategory> result(){
        return categories;
    }
}
