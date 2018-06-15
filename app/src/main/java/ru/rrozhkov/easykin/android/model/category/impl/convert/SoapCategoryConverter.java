package ru.rrozhkov.easykin.android.model.category.impl.convert;

import org.ksoap2.serialization.SoapObject;

import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class SoapCategoryConverter implements IConverter<SoapObject, ICategory> {
    final static private CategoryFactory categoryFactory = new CategoryFactory();
    @Override
    public ICategory convert(SoapObject object) {
        return categoryFactory.create(
                Integer.valueOf(object.getProperty("id").toString())
                ,object.getProperty("name").toString());
    }
}
