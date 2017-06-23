package ru.rrozhkov.easykin.android.context;

import java.util.Collection;

import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.fin.payment.PaymentStatus;
import ru.rrozhkov.easykin.model.fin.payment.impl.filter.PaymentFilterFactory;
import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Status;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.filter.util.FilterUtil;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public abstract class MasterDataContext {
    protected Collection<ICategory> categories = CollectionUtil.create();
    protected Collection<ITask> tasks = CollectionUtil.create();
    protected Collection<IPayment> payments = CollectionUtil.create();
    protected Collection<IPerson> persons = CollectionUtil.create();

    abstract public void init();

    abstract public void replicate();

    abstract public void dump();

    public Collection<ICategory> categories() {
        return categories;
    }

    public Collection<ITask> tasks() {
        return tasks;
    }

    public Collection<IPayment> payments() {
        return payments;
    }

    public Collection<IPerson> persons(){
        return persons;
    }

    public Collection<IPayment> finance() {
        return FilterUtil.filter(payments(), PaymentFilterFactory.status(PaymentStatus.PLAN), PaymentFilterFactory.noFree());
    }

    public Collection<IPayment> factPayments() {
        return FilterUtil.filter(payments(), PaymentFilterFactory.status(PaymentStatus.FACT), PaymentFilterFactory.noFree());
    }
    public Status[] statuses(){
        return new ru.rrozhkov.easykin.model.task.Status[]{
                Status.OPEN,
                Status.CLOSE
        };
    }
}
