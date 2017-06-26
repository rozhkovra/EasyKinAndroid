package ru.rrozhkov.easykin.android.db.impl;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rrozhkov on 6/19/2017.
 */

public class DumpManager {
    public static String dump = "INSERT INTO category(id, name) VALUES(1, 'Дом');INSERT INTO category(id, name) VALUES(2, 'Дети');INSERT INTO category(id, name) VALUES(3, 'Семья');INSERT INTO category(id, name) VALUES(4, 'Машина');INSERT INTO category(id, name) VALUES(5, 'Финансы');INSERT INTO category(id, name) VALUES(6, 'Платежи');INSERT INTO category(id, name) VALUES(7, 'Документы');INSERT INTO category(id, name) VALUES(8, 'Работа');INSERT INTO category(id, name) VALUES(9, 'Задачи');INSERT INTO category(id, name) VALUES(10, 'Коммунальные услуги');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(3, 'Берсенева', 'Серафима', 'Андреевна', 'Ж', 'null', 'null');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(4, 'Рожков', 'Алексей', 'Романович', 'М', 'null', 'null');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(1, 'Рожков', 'Роман', 'Александрович', 'М', 'rrozhkov', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(2, 'Берсенева', 'Татьяна', 'Михайловна', 'Ж', 'tberseneva', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=');";

    public static void restoreDump(EasyKinDBHelper dbHelper){
//        String dump = readDump();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(!db.isOpen())
            return;
        String[] sqls = dump.split(";");
        try {
            for (String sql : sqls) {
                db.execSQL(sql);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.close();
        }
    }
}
