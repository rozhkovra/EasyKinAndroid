package ru.rrozhkov.easykin.android.model.person.impl.convert;

import org.ksoap2.serialization.SoapObject;

import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.person.Sex;
import ru.rrozhkov.easykin.model.person.impl.PersonFactory;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class SoapPersonConverter implements IConverter<SoapObject, IPerson> {
    @Override
    public IPerson convert(SoapObject object) {
        return PersonFactory.create(
                Integer.valueOf(object.getProperty("id").toString())
                ,object.getProperty("surname").toString()
                ,object.getProperty("name").toString()
                ,object.getProperty("secondname").toString()
                , null
                ,Sex.sex(object.getProperty("sex").toString())
                ,object.getProperty("username").toString()
                ,object.getProperty("password").toString());
    }
}
