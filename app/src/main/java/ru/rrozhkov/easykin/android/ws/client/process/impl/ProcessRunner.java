package ru.rrozhkov.easykin.android.ws.client.process.impl;

import android.os.AsyncTask;

import ru.rrozhkov.easykin.core.timer.Timer;
import ru.rrozhkov.easykin.core.ws.process.IProcessor;


/**
 * Created by rrozhkov on 2/22/2017.
 */

public class ProcessRunner {
    private IProcessor processor;

    public ProcessRunner(IProcessor processor) {
        this.processor = processor;
    }

    public void run(){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                processor.process();
                return null;
            }
        };
        task.execute();
        Timer timer = new Timer(10000);
        while(!processor.isComplete() && !timer.isExpired()){

        }
    }
}
