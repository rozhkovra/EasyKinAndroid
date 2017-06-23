package ru.rrozhkov.easykin.android.model.person.impl.convert;

import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 6/19/2017.
 */
public class PersonInsertConverter implements IConverter<IPerson, String>{
    @Override
    public String convert(IPerson iPerson) {
        return "INSERT INTO person (id, surname, name, secondname, sex, username, password"
                +"VALUES("+iPerson.getId()
                +", '"+iPerson.getSurname()+"'"
                +", '"+iPerson.getName()+"'"
                +", '"+iPerson.getSecondName()+"'"
                +", '"+iPerson.getSex()+"'"
                +", '"+iPerson.getUsername()+"'"
                +", '"+iPerson.getPassword()+"')";
    }
}
