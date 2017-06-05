package ru.rrozhkov.easykin.android.db.impl;

import java.sql.SQLException;

import ru.rrozhkov.easykin.android.service.EasyKinAuthService;

/**
 * Created by rrozhkov on 6/5/2017.
 */

public class DBEasyKinAuthService implements EasyKinAuthService {
    @Override
    public int auth(String user, String pass) {
        try {
            return AuthHandler.auth(user, pass)!=null?1:-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
