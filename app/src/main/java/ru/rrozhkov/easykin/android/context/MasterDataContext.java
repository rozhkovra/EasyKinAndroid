package ru.rrozhkov.easykin.android.context;

import java.util.Collection;

import ru.rrozhkov.easykin.fin.payment.impl.filter.PaymentFilterFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.fin.payment.PaymentStatus;

import ru.rrozhkov.easykin.model.person.IPerson;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Status;
import ru.rrozhkov.easykin.core.collection.CollectionUtil;
import ru.rrozhkov.easykin.core.filter.util.FilterUtil;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public abstract class MasterDataContext {
    protected Collection<ICategory> categories = CollectionUtil.create();
    protected Collection<ITask> tasks = CollectionUtil.create();
    protected Collection<IPayment> payments = CollectionUtil.create();
    protected Collection<IPerson> persons = CollectionUtil.create();
    private static final PaymentFilterFactory paymentFilterFactory = PaymentFilterFactory.instance();

    abstract public void init();

    abstract public void replicate();

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
        return FilterUtil.filter(payments(), paymentFilterFactory.status(PaymentStatus.PLAN), paymentFilterFactory.noFree());
    }

    public Collection<IPayment> factPayments() {
        return FilterUtil.filter(payments(), paymentFilterFactory.status(PaymentStatus.FACT), paymentFilterFactory.noFree());
    }
    public Status[] statuses(){
        return new ru.rrozhkov.easykin.model.task.Status[]{
                Status.OPEN,
                Status.CLOSE
        };
    }
}
