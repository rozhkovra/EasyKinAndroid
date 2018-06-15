package ru.rrozhkov.easykin.android.model.category.impl.convert;

import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.category.ICategory;

/**
 * Created by rrozhkov on 6/19/2017.
 */

public class CategoryInsertConverter implements IConverter<ICategory, String> {
    @Override
    public String convert(ICategory category) {
        return "INSERT INTO category(id, name) VALUES("+category.getId()+", '"+category.getName()+"')";
    }
}
