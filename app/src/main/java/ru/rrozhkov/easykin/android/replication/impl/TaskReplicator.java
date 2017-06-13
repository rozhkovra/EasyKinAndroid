package ru.rrozhkov.easykin.android.replication.impl;

import java.sql.SQLException;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.db.impl.PersonHandler;
import ru.rrozhkov.easykin.android.db.impl.TaskHandler;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.task.ITask;

/**
 * Created by rrozhkov on 6/2/2017.
 */

public class TaskReplicator {
    protected MasterDataContext soapMasterDataContext;

    public TaskReplicator(MasterDataContext soapMasterDataContext) {
        this.soapMasterDataContext = soapMasterDataContext;
    }

    public void execute(){
        try {
            new TaskHandler().deleteAll();
            for(ITask task : soapMasterDataContext.tasks()){
                new TaskHandler().insert(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
