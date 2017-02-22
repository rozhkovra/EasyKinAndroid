package ru.rrozhkov.easykin.android.ws.client.process;

import java.util.Collection;

/**
 * Created by rrozhkov on 2/22/2017.
 */

public interface IProcessor {
    void process();
    Collection result();
    boolean isComplete();
}
