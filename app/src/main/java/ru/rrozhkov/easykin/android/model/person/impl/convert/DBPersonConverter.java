package ru.rrozhkov.easykin.android.model.person.impl.convert;

import android.database.Cursor;

import java.util.Date;

import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.person.Sex;
import ru.rrozhkov.easykin.model.person.impl.PersonFactory;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBPersonConverter implements IConverter<Cursor, IPerson> {
    @Override
    public IPerson convert(Cursor cursor) {
        return PersonFactory.create(cursor.getInt(cursor.getColumnIndex("id"))
                , cursor.getString(cursor.getColumnIndex("surname"))
                , cursor.getString(cursor.getColumnIndex("name"))
                , cursor.getString(cursor.getColumnIndex("secondname"))
                , new Date()
                , Sex.sex(cursor.getString(cursor.getColumnIndex("sex")))
                , cursor.getString(cursor.getColumnIndex("username"))
                , cursor.getString(cursor.getColumnIndex("password")));
    }
}
