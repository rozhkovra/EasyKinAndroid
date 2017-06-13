package ru.rrozhkov.easykin.android.context.impl;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.replication.impl.CategoryReplicator;
import ru.rrozhkov.easykin.android.replication.impl.PersonReplicator;
import ru.rrozhkov.easykin.android.replication.impl.TaskReplicator;
import ru.rrozhkov.easykin.android.service.EasyKinService;
import ru.rrozhkov.easykin.android.ws.client.SOAPEasyKinService;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class SOAPMasterDataContext extends MasterDataContext {
    private EasyKinService service = new SOAPEasyKinService();

    @Override
    public void init() {
        this.categories = service.categories();
        this.tasks = service.tasks();
        this.payments = service.payments();
        this.persons = service.persons();
    }

    @Override
    public void replicate() {
        PersonReplicator pReplicator = new PersonReplicator(this);
        pReplicator.execute();
        CategoryReplicator replicator = new CategoryReplicator(this);
        replicator.execute();
        TaskReplicator tReplicator = new TaskReplicator(this);
        tReplicator.execute();
    }
}
