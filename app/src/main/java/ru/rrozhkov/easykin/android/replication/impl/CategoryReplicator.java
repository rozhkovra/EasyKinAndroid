package ru.rrozhkov.easykin.android.replication.impl;

import java.sql.SQLException;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.db.impl.CategoryHandler;
import ru.rrozhkov.easykin.model.category.ICategory;

/**
 * Created by rrozhkov on 5/30/2017.
 */

public class CategoryReplicator {
    protected MasterDataContext soapMasterDataContext;

    public CategoryReplicator(MasterDataContext soapMasterDataContext) {
        this.soapMasterDataContext = soapMasterDataContext;
    }

    public void execute(){
        try {
            new CategoryHandler().deleteAll();
            for(ICategory category : soapMasterDataContext.categories()){
                new CategoryHandler().insert(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
