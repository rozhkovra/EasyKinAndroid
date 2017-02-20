package ru.rrozhkov.easykin.android.ws.client.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class DateUtil {
    private static final DateFormat SDFWS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");

    public static Date parseWs(String date){
        try{
            return SDFWS.parse(date);
        }catch(Exception e){
            return null;
        }
    }
}
