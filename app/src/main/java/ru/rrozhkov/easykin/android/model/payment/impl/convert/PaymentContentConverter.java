package ru.rrozhkov.easykin.android.model.payment.impl.convert;

import android.content.ContentValues;

import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.fin.payment.PaymentCategory;
import ru.rrozhkov.easykin.model.fin.payment.PaymentStatus;


/**
 * Created by rrozhkov on 5/30/2017.
 */

public class PaymentContentConverter implements IConverter<IPayment, ContentValues> {
    @Override
    public ContentValues convert(IPayment payment) {
        ContentValues map = new ContentValues();
        map.put("id", payment.getId());
        map.put("comment", payment.getComment());
        map.put("category", PaymentCategory.category(payment.getCategory()));
        map.put("status", PaymentStatus.status(payment.getStatus()));
        map.put("amount", payment.getAmount().getValue());
        return map;
    }
}
