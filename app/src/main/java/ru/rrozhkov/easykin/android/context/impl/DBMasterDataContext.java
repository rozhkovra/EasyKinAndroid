package ru.rrozhkov.easykin.android.context.impl;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.db.impl.DBEasyKinService;
import ru.rrozhkov.easykin.android.service.EasyKinService;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBMasterDataContext extends MasterDataContext {
    private EasyKinService service = new DBEasyKinService();

    @Override
    public void init() {
        this.categories = service.categories();
        this.tasks = service.tasks();
        this.payments = service.payments();
    }

    @Override
    public void replicate() {

    }
}