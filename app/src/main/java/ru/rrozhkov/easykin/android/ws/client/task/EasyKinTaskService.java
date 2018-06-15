package ru.rrozhkov.easykin.android.ws.client.task;

import ru.rrozhkov.easykin.android.ws.client.process.impl.ProcessRunner;
import ru.rrozhkov.easykin.android.ws.client.task.process.impl.AddTaskProcessor;
import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.core.ws.process.IProcessor;
import ru.rrozhkov.easykin.model.task.ITask;

/**
 * Created by rrozhkov on 2/28/2017.
 */

public class EasyKinTaskService {
    private static final String NAMESPACE = "http://rrozhkov.ru/easykin/task";
    private static final String URL = "http://172.31.46.6:8081/EasyKinService/task?wsdl";

    public int add(ITask task){
        final IProcessor processor = new AddTaskProcessor(task, NAMESPACE, URL);
        ProcessRunner runner = new ProcessRunner(processor);
        runner.run();
        return (Integer) CollectionUtil.get(processor.result(),0);
    }
}
