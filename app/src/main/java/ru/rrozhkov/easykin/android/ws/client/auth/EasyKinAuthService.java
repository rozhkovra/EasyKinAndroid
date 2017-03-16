package ru.rrozhkov.easykin.android.ws.client.auth;

import ru.rrozhkov.easykin.android.ws.client.auth.process.impl.AuthProcessor;
import ru.rrozhkov.easykin.android.ws.client.process.impl.ProcessRunner;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.ws.process.IProcessor;

/**
 * Created by rrozhkov on 2/28/2017.
 */

public class EasyKinAuthService{
    private static final String NAMESPACE = "http://rrozhkov.ru/easykin/auth";
    private static final String URL = "http://172.31.46.6:8081/EasyKinService/auth?wsdl";

    public int auth(String user, String pass){
        final IProcessor processor = new AuthProcessor(user, pass, NAMESPACE, URL);
        ProcessRunner runner = new ProcessRunner(processor);
        runner.run();
        return (Integer)CollectionUtil.get(processor.result(),0);
    }
}
