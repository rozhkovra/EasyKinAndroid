package ru.rrozhkov.easykin.android.db.impl;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.easykin.android.model.person.impl.convert.DBPersonConverter;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.lib.android.crypt.AndroidEncrypter;
import ru.rrozhkov.lib.collection.CollectionUtil;

/**
 * Created by rrozhkov on 6/5/2017.
 */

public class AuthHandler {
    private static String select = "select * from person where username='#username#' and password='#password#'";
    public static IPerson auth(String username, String password) throws SQLException {
        Collection<IPerson> persons = EasyKinDBManager.instance().select(
                select.replace("#username#",username).replace("#password#", AndroidEncrypter.encrypt(password))
                , new DBPersonConverter());
        if(persons.isEmpty() || persons.size()>1)
            return null;
        return CollectionUtil.get(persons,0);
    }
}
