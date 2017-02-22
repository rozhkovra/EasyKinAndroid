package ru.rrozhkov.easykin.android.model.payment.impl.convert;

import java.util.Collection;

import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 2/20/2017.
 */

public class PaymentArrayConverter implements IConverter<Collection<IPayment>, String[]> {
    @Override
    public String[] convert(Collection<IPayment> payments) {
        Collection<String> cats = CollectionUtil.create();
        for(IPayment bean : payments){
            cats.add(bean.getComment()+" | "+bean.getAmount());
        }
        return cats.toArray(new String[payments.size()]);
    }
}
