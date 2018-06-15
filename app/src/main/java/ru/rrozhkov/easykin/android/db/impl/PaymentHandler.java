package ru.rrozhkov.easykin.android.db.impl;

import android.content.ContentValues;
import android.database.Cursor;

import ru.rrozhkov.easykin.android.model.payment.impl.convert.DBPaymentConverter;
import ru.rrozhkov.easykin.android.model.payment.impl.convert.PaymentContentConverter;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.lib.android.db.impl.EntityHandler;


/**
 * Created by rrozhkov on 6/1/2017.
 */

public class PaymentHandler extends EntityHandler<IPayment>{
    @Override
    public String tableName() {
        return "payment";
    }

    @Override
    public IConverter<IPayment, ContentValues> converter() {
        return new PaymentContentConverter();
    }

    @Override
    public IConverter<Cursor, IPayment> dbConverter() {
        return new DBPaymentConverter();
    }
}
