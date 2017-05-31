package ru.rrozhkov.easykin.android.db.impl;

import ru.rrozhkov.lib.android.db.impl.DBManager;

/**
 * Created by rrozhkov on 5/30/2017.
 */

public class EasyKinDBManager extends DBManager {
    private static EasyKinDBManager dbManager;

    public EasyKinDBManager(EasyKinDBHelper dbHelper) {
        super(dbHelper);
    }

    public static void init(EasyKinDBHelper dbHelper){
        dbManager = new EasyKinDBManager(dbHelper);
    }

    public static EasyKinDBManager instance(){
        return dbManager;
    }
}
