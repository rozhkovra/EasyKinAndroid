package ru.rrozhkov.easykin.android.db.impl;

import java.sql.SQLException;
import java.util.Collection;

import ru.rrozhkov.easykin.android.service.EasyKinService;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.core.collection.CollectionUtil;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBEasyKinService implements EasyKinService {
    public Collection<ICategory> categories() {
        try {
            return new CategoryHandler().select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CollectionUtil.create();
    }
    public Collection<ITask> tasks() {
        try {
            return new TaskHandler().select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CollectionUtil.create();
    }
    public Collection<IPayment> payments() {
        try {
            return new PaymentHandler().select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CollectionUtil.create();
    }
    public Collection<IPerson> persons() {
        try {
            return new PersonHandler().select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CollectionUtil.create();
    }
}
