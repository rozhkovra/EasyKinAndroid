package ru.rrozhkov.easykin.android.ws.client;

import android.os.AsyncTask;

import java.util.Collection;

import ru.rrozhkov.easykin.android.ws.client.bean.CategoryBean;
import ru.rrozhkov.easykin.android.ws.client.bean.PersonBean;
import ru.rrozhkov.easykin.android.ws.client.bean.TaskBean;
import ru.rrozhkov.easykin.android.ws.client.process.CategoryProcessor;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class EasyKinService {
    private static final String NAMESPACE = "http://rrozhkov.ru/easykin";
    private static final String URL = "http://172.31.46.6:8081/EasyKinService/?wsdl";

    public Collection<CategoryBean> categories() {
        final CategoryProcessor processor = new CategoryProcessor(NAMESPACE, URL);
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                processor.process();
                processor.setComplete(true);
                return null;
            }
        };
        task.execute();
        while(!processor.isComplete()){

        }
        return processor.result();
    }
    public Collection<PersonBean> persons() {
        return null;
    }
    public Collection<TaskBean> tasks() {
        return null;
    }
}
