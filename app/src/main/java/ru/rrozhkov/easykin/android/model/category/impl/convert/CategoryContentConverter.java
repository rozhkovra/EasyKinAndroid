package ru.rrozhkov.easykin.android.model.category.impl.convert;

import android.content.ContentValues;

import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 5/30/2017.
 */

public class CategoryContentConverter implements IConverter<ICategory, ContentValues> {
    @Override
    public ContentValues convert(ICategory category) {
        ContentValues map = new ContentValues();
        map.put("id", category.getId());
        map.put("name", category.getName());
        return map;
    }
}
