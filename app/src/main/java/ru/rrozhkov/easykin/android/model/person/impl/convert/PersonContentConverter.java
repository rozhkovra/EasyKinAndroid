package ru.rrozhkov.easykin.android.model.person.impl.convert;

import android.content.ContentValues;

import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.person.IPerson;

/**
 * Created by rrozhkov on 5/30/2017.
 */

public class PersonContentConverter implements IConverter<IPerson, ContentValues> {
    @Override
    public ContentValues convert(IPerson person) {
        ContentValues map = new ContentValues();
        map.put("id", person.getId());
        map.put("surname", person.getSurname());
        map.put("name", person.getName());
        map.put("secondname", person.getSecondName());
        map.put("sex", person.getSex().toString());
        map.put("username", person.getUsername());
        map.put("password", person.getPassword());
        return map;
    }
}
