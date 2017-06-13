package ru.rrozhkov.easykin.android.model.person.impl.convert;

import org.ksoap2.serialization.SoapObject;

import java.util.Date;

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
                Integer.valueOf(object.getPropertySafelyAsString("id"))
                ,object.getPropertySafelyAsString("surname")
                ,object.getPropertySafelyAsString("name")
                ,object.getPropertySafelyAsString("secondName")
                ,new Date()
                ,Sex.sex(object.getPropertySafelyAsString("sex"))
                ,object.getPropertySafelyAsString("username")
                ,object.getPropertySafelyAsString("password"));
    }
}
