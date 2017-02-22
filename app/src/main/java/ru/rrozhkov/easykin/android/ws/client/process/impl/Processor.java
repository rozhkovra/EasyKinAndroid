package ru.rrozhkov.easykin.android.ws.client.process.impl;

import java.util.Collection;

import ru.rrozhkov.easykin.android.ws.client.process.IProcessor;
import ru.rrozhkov.easykin.model.task.ITask;

/**
 * Created by rrozhkov on 2/22/2017.
 */
public abstract class Processor implements IProcessor {
    protected String namespace;
    protected String url;
    protected boolean complete = false;

    public Processor(String namespace, String url) {
        this.namespace = namespace;
        this.url = url;
    }

    public void setComplete(boolean complete){
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }
}
