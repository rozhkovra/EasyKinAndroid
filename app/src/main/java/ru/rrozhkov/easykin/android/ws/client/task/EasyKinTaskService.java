package ru.rrozhkov.easykin.android.ws.client.task;

import ru.rrozhkov.easykin.android.ws.client.process.impl.ProcessRunner;
import ru.rrozhkov.easykin.android.ws.client.task.process.impl.AddTaskProcessor;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.ws.process.IProcessor;

/**
 * Created by rrozhkov on 2/28/2017.
 */

public class EasyKinTaskService {
    private static final String NAMESPACE = "http://rrozhkov.ru/easykin/task";
    private static final String URL = "http://172.31.46.6:8082/EasyKinTaskService/?wsdl";

    public int add(ITask task){
        final IProcessor processor = new AddTaskProcessor(task, NAMESPACE, URL);
        ProcessRunner runner = new ProcessRunner(processor);
        runner.run();
        return 0;
    }
}
