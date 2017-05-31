package ru.rrozhkov.easykin.android.context.impl;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.ws.client.util.ServiceUtil;

/**
 * Created by rrozhkov on 5/31/2017.
 */

public class MasterDataContextFactory {
    public static MasterDataContext instance(){
        if(ServiceUtil.isServiceAvailable())
            return new SOAPMasterDataContext();
        return new DBMasterDataContext();
    }
}
