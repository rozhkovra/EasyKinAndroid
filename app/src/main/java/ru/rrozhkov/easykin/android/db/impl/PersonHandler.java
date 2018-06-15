package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import ru.rrozhkov.easykin.android.model.person.impl.convert.DBPersonConverter;
import ru.rrozhkov.easykin.android.model.person.impl.convert.PersonContentConverter;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.lib.android.db.impl.EntityHandler;


/**
 * Created by rrozhkov on 6/1/2017.
 */

public class PersonHandler extends EntityHandler<IPerson>{
    @Override
    public String tableName() {
        return "person";
    }

    @Override
    public IConverter<IPerson, ContentValues> converter() {
        return new PersonContentConverter();
    }

    @Override
    public IConverter<Cursor, IPerson> dbConverter() {
        return new DBPersonConverter();
    }
}
