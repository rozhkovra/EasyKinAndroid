package ru.rrozhkov.easykin.android.model.category.impl.convert;

import java.util.Collection;

import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class CategoryArrayConverter implements IConverter<Collection<ICategory>, String[]> {
    @Override
    public String[] convert(Collection<ICategory> categories) {
        Collection<String> cats = CollectionUtil.create();
        for(ICategory bean : categories){
            cats.add(bean.getName());
        }
        return cats.toArray(new String[categories.size()]);
    }
}
