package ru.rrozhkov.easykin.android.model.payment.impl.convert;

import android.database.Cursor;

import java.util.Date;

import ru.rrozhkov.easykin.model.fin.MoneyFactory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.fin.payment.PaymentCategory;
import ru.rrozhkov.easykin.model.fin.payment.PaymentStatus;
import ru.rrozhkov.easykin.model.fin.payment.impl.PaymentFactory;
import ru.rrozhkov.lib.convert.IConverter;

/**
 * Created by rrozhkov on 5/18/2017.
 */

public class DBPaymentConverter implements IConverter<Cursor, IPayment> {
    @Override
    public IPayment convert(Cursor cursor) {
        return PaymentFactory.createPayment(cursor.getInt(cursor.getColumnIndex("id"))
                , PaymentCategory.category(cursor.getInt(cursor.getColumnIndex("category")))
                , cursor.getString(cursor.getColumnIndex("comment"))
                , MoneyFactory.create(cursor.getDouble(cursor.getColumnIndex("amount")))
                , new Date()
                , PaymentStatus.status(cursor.getInt(cursor.getColumnIndex("status"))));
    }
}
