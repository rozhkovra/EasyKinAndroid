package ru.rrozhkov.easykin.android.model.payment.impl.convert;

import org.ksoap2.serialization.SoapObject;

import ru.rrozhkov.easykin.android.ws.client.util.DateUtil;
import ru.rrozhkov.easykin.core.convert.IConverter;
import ru.rrozhkov.easykin.model.fin.Money;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.fin.payment.PaymentCategory;
import ru.rrozhkov.easykin.model.fin.payment.PaymentStatus;
import ru.rrozhkov.easykin.model.fin.payment.impl.PaymentFactory;


/**
 * Created by rrozhkov on 2/22/2017.
 */
public class SoapPaymentConverter implements IConverter<SoapObject, IPayment> {
    final static private PaymentFactory paymentFactory = PaymentFactory.instance();
    @Override
    public IPayment convert(SoapObject object) {
        IPayment payment = null;
        if(PaymentCategory.AUTO.toString().equals(object.getProperty("category").toString())){
            payment = paymentFactory.createPayment(PaymentCategory.AUTO
                    , object.getProperty("comment").toString()
                    , Money.valueOf(Double.valueOf(object.getProperty("amount").toString()))
                    , DateUtil.parseWs(object.getProperty("date").toString())
                    , PaymentStatus.status(Integer.valueOf(object.getProperty("status").toString()))
            ) ;
        }
        if(PaymentCategory.AUTODETAIL.toString().equals(object.getProperty("category").toString())){
            payment = paymentFactory.createPayment(PaymentCategory.AUTODETAIL
                    , object.getProperty("comment").toString()
                    , Money.valueOf(Double.valueOf(object.getProperty("amount").toString()))
                    , DateUtil.parseWs(object.getProperty("date").toString())
                    , PaymentStatus.status(Integer.valueOf(object.getProperty("status").toString()))
            ) ;
        }
        if(PaymentCategory.AUTOREPAIR.toString().equals(object.getProperty("category").toString())){
            payment = paymentFactory.createPayment(PaymentCategory.AUTOREPAIR
                    , object.getProperty("comment").toString()
                    , Money.valueOf(Double.valueOf(object.getProperty("amount").toString()))
                    , DateUtil.parseWs(object.getProperty("date").toString())
                    , PaymentStatus.status(Integer.valueOf(object.getProperty("status").toString()))
            ) ;
        }
        if(PaymentCategory.SERVICE.toString().equals(object.getProperty("category").toString())){
            payment = paymentFactory.createPayment(PaymentCategory.SERVICE
                    , object.getProperty("comment").toString()
                    , Money.valueOf(Double.valueOf(object.getProperty("amount").toString()))
                    , DateUtil.parseWs(object.getProperty("date").toString())
                    , PaymentStatus.status(Integer.valueOf(object.getProperty("status").toString()))
            ) ;
        }
        if(PaymentCategory.TASK.toString().equals(object.getProperty("category").toString())){
            payment = paymentFactory.createPayment(PaymentCategory.TASK
                    , object.getProperty("comment").toString()
                    , Money.valueOf(Double.valueOf(object.getProperty("amount").toString()))
                    , DateUtil.parseWs(object.getProperty("date").toString())
                    , PaymentStatus.status(Integer.valueOf(object.getProperty("status").toString()))
            ) ;
        }
        return payment;
    }
}
