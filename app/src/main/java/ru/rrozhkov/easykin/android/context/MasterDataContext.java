package ru.rrozhkov.easykin.android.context;

import java.util.Collection;

import ru.rrozhkov.easykin.android.ws.client.EasyKinService;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.fin.payment.PaymentStatus;
import ru.rrozhkov.easykin.model.fin.payment.impl.filter.PaymentFilterFactory;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.filter.util.FilterUtil;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class MasterDataContext {
    private EasyKinService service = new EasyKinService();
    private Collection<ICategory> categories = CollectionUtil.create();
    private Collection<ITask> tasks = CollectionUtil.create();
    private Collection<IPayment> payments = CollectionUtil.create();

    public void init(){
        this.categories = service.categories();
        this.tasks = service.tasks();
        this.payments = service.payments();
    }

    public Collection<ICategory> categories() {
        return categories;
    }

    public Collection<ITask> tasks() {
        return tasks;
    }

    public Collection<IPayment> payments() {
        return payments;
    }

    public Collection<IPayment> finance() {
        return FilterUtil.filter(payments(), PaymentFilterFactory.status(PaymentStatus.PLAN), PaymentFilterFactory.noFree());
    }

    public Collection<IPayment> factPayments() {
        return FilterUtil.filter(payments(), PaymentFilterFactory.status(PaymentStatus.FACT), PaymentFilterFactory.noFree());
    }

}
