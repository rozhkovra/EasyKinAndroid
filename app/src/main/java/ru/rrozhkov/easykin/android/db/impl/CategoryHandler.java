package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import ru.rrozhkov.easykin.android.model.category.impl.convert.CategoryContentConverter;
import ru.rrozhkov.easykin.android.model.category.impl.convert.DBCategoryConverter;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.lib.android.db.impl.EntityHandler;
import ru.rrozhkov.easykin.core.convert.IConverter;

/**
 * Created by rrozhkov on 5/30/2017.
 */

public class CategoryHandler extends EntityHandler<ICategory>{
    @Override
    public String tableName() {
        return "category";
    }

    @Override
    public IConverter<ICategory, ContentValues> converter() {
        return new CategoryContentConverter();
    }

    @Override
    public IConverter<Cursor, ICategory> dbConverter() {
        return new DBCategoryConverter();
    }
}
