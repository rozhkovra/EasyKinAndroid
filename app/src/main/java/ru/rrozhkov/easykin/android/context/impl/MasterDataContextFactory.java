package ru.rrozhkov.easykin.android.context.impl;

import ru.rrozhkov.easykin.android.context.MasterDataContext;

/**
 * Created by rrozhkov on 5/31/2017.
 */

public class MasterDataContextFactory {
    public static MasterDataContext instance(boolean online){
        if(online)
            return new SOAPMasterDataContext();
        return new DBMasterDataContext();
    }
}
