package ru.rrozhkov.easykin.android.ws.client.util;

import ru.rrozhkov.easykin.android.ws.client.EasyKinService;

/**
 * Created by rrozhkov on 5/17/2017.
 */

public class ServiceUtil {
    public static boolean isServiceAvailable(){
        return new EasyKinService().ping()==1;
    }
}
