package ru.rrozhkov.easykin.android.service;

import java.util.Collection;

import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public interface EasyKinService {
    Collection<ICategory> categories();
    Collection<ITask> tasks();
    Collection<IPayment> payments();
}
