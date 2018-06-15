package ru.rrozhkov.easykin.android.model.person.impl.convert;

import android.database.Cursor;

import java.util.Date;

import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.person.Sex;
import ru.rrozhkov.easykin.model.person.impl.PersonFactory;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBPersonConverter implements IConverter<Cursor, IPerson> {
    final static private PersonFactory personFactory = new PersonFactory();
    @Override
    public IPerson convert(Cursor cursor) {
        return personFactory.create(cursor.getInt(cursor.getColumnIndex("id"))
                , cursor.getString(cursor.getColumnIndex("surname"))
                , cursor.getString(cursor.getColumnIndex("name"))
                , cursor.getString(cursor.getColumnIndex("secondname"))
                , new Date()
                , Sex.sex(cursor.getString(cursor.getColumnIndex("sex")))
                , cursor.getString(cursor.getColumnIndex("username"))
                , cursor.getString(cursor.getColumnIndex("password")));
    }
}
