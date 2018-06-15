package ru.rrozhkov.easykin.android.model.category.impl.convert;

import android.database.Cursor;

import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.core.convert.IConverter;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBCategoryConverter implements IConverter<Cursor, ICategory> {
    final static private CategoryFactory categoryFactory = new CategoryFactory();
    @Override
    public ICategory convert(Cursor cursor) {
        return categoryFactory.create(cursor.getInt(cursor.getColumnIndex("id"))
                , cursor.getString(cursor.getColumnIndex("name")));
    }
}
