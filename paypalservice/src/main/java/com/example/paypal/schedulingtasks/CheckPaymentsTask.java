package com.example.paypal.schedulingtasks;


import com.example.paypal.config.VarConfig;
import com.example.paypal.model.MyPayment;
import com.example.paypal.repository.PaymentRepository;
import com.example.paypal.service.PaymentService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CheckPaymentsTask {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Scheduled(fixedRate = 60000)   //na 1 min
    public void checkPayments() {
        getCurrentTimeUsingDate();
        APIContext apiContext = new APIContext(VarConfig.clinetId, VarConfig.secret, VarConfig.mode);
        List<MyPayment> createdPayments = paymentService.findAllByStatus(VarConfig.paymentStatusCreated);
        for(MyPayment p : createdPayments){
            String message = "Payment: " + p.getId() + " \t " + p.getStatus() + "\t->\t";
            try {
                Payment payment = Payment.get(apiContext, p.getPaymentId());
                p.setStatus(payment.getState());
                paymentRepository.save(p);
                message += p.getStatus();
                System.out.println(message);
            }
            catch (PayPalRESTException e){
                System.out.println(e.getDetails());
            }
        }
        System.out.println("--------------------------------------------------\n");
    }

    public static void getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        System.out.println("-------------------- " + formattedDate + " --------------------");
    }
}
