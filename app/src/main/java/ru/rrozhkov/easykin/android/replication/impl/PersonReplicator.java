package ru.rrozhkov.easykin.android.replication.impl;

import java.sql.SQLException;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.db.impl.PersonHandler;
import ru.rrozhkov.easykin.model.person.IPerson;

/**
 * Created by rrozhkov on 6/2/2017.
 */

public class PersonReplicator {
    protected MasterDataContext soapMasterDataContext;

    public PersonReplicator(MasterDataContext soapMasterDataContext) {
        this.soapMasterDataContext = soapMasterDataContext;
    }

    public void execute(){
        try {
            new PersonHandler().deleteAll();
            for(IPerson person : soapMasterDataContext.persons()){
                new PersonHandler().insert(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
