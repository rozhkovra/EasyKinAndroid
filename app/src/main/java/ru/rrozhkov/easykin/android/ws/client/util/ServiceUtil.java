package ru.rrozhkov.easykin.android.ws.client.util;

import ru.rrozhkov.easykin.android.ws.client.SOAPEasyKinService;

/**
 * Created by rrozhkov on 5/17/2017.
 */

public class ServiceUtil {
    public static boolean isServiceAvailable(){
        return new SOAPEasyKinService().ping()==1;
    }
}
