package ru.rrozhkov.easykin.android.db.impl;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ru.rrozhkov.easykin.android.FilesSettings;

/**
 * Created by rrozhkov on 6/19/2017.
 */

public class DumpManager {
    public static String dump = "INSERT INTO person VALUES(1,'Рожков','Роман','Александрович','M','rrozhkov','QL0AFWMIX8NRZTKeof9cXsvbvu8=')";

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

    public static String readDump(){
        File sdcard = Environment.getExternalStorageDirectory();
        File dumpFile = new File(sdcard, FilesSettings.EASYKIN_DUMP);

        StringBuilder dump = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(dumpFile));
            String line;

            while ((line = br.readLine()) != null) {
                dump.append(line);
                dump.append('\n');
            }
            br.close();
        }catch(IOException e){

        }
        return dump.toString();
    }
}
