package ru.rrozhkov.easykin.android.ws.client.time;

import java.util.Date;

/**
 * Created by rrozhkov on 2/22/2017.
 */

public class Timer {
    private Date start;
    private int timeout;

    public Timer(int timeout) {
        this.timeout = timeout;
        this.start = new Date();
    }

    public boolean isExpired(){
        return new Date().getTime() - start.getTime() > timeout;
    }
}
