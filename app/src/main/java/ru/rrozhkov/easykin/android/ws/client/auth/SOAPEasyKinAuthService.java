package ru.rrozhkov.easykin.android.ws.client.auth;

import ru.rrozhkov.easykin.android.service.EasyKinAuthService;
import ru.rrozhkov.easykin.android.ws.client.auth.process.impl.AuthProcessor;
import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.core.ws.process.IProcessor;

/**
 * Created by rrozhkov on 2/28/2017.
 */

public class SOAPEasyKinAuthService implements EasyKinAuthService{
    private static final String NAMESPACE = "http://rrozhkov.ru/easykin/auth";
    private static final String URL = "http://172.31.46.6:8081/EasyKinService/auth?wsdl";

    public int auth(String user, String pass){
        final IProcessor processor = new AuthProcessor(user, pass, NAMESPACE, URL);
        processor.process();
        return (Integer) CollectionUtil.get(processor.result(),0);
    }
}
