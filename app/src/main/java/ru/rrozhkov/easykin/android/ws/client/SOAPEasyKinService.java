package ru.rrozhkov.easykin.android.ws.client;

import java.util.Collection;

import ru.rrozhkov.easykin.android.service.EasyKinService;
import ru.rrozhkov.easykin.android.ws.client.process.impl.CategoryProcessor;
import ru.rrozhkov.easykin.android.ws.client.process.impl.PaymentProcessor;
import ru.rrozhkov.easykin.android.ws.client.process.impl.PingProcessor;
import ru.rrozhkov.easykin.android.ws.client.process.impl.ProcessRunner;
import ru.rrozhkov.easykin.android.ws.client.process.impl.TaskProcessor;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.ws.process.IProcessor;

/**
 * Created by rrozhkov on 2/17/2017.
 */

public class SOAPEasyKinService implements EasyKinService {
    private static final String NAMESPACE = "http://rrozhkov.ru/easykin";
    private static final String URL = "http://172.31.46.6:8081/EasyKinService/?wsdl";

    public Collection<ICategory> categories() {
        final IProcessor processor = new CategoryProcessor(NAMESPACE, URL);
        processor.process();
        return processor.result();
    }
    public Collection<ITask> tasks() {
        final IProcessor processor = new TaskProcessor(NAMESPACE, URL);
        processor.process();
        return processor.result();
    }
    public Collection<IPayment> payments() {
        final IProcessor processor = new PaymentProcessor(NAMESPACE, URL);
        processor.process();
        return processor.result();
    }
    public int ping(){
        final IProcessor processor = new PingProcessor(NAMESPACE, URL);
        processor.process();
        return (Integer)CollectionUtil.get(processor.result(),0);
    }
}
