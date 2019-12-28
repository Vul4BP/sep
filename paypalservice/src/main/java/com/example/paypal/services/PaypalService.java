package com.example.paypal.services;

import com.example.paypal.config.PaypalConfig;
import com.example.paypal.model.enumeration.PaymentIntent;
import com.example.paypal.model.enumeration.PaymentMethod;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {

    public String createPayment(Double total, String currency, String method,
                                 String intent, String description, String cancelUrl, String successUrl)
    {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(total.toString());

        Payee payee = new Payee();
        payee.setEmail("sb-4t1vg791084@business.example.com");

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setPayee(payee);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setIntent(intent);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);


        APIContext apiContext = new APIContext("AbzohpnhZ3DCLLCVhbI7OlW3wpsqZdSEGql5YXnhh01S4KUKXo088-YkOv6E5HzY6arAxmYhkL8hiaI4",
                "XvdfrGw8IWJKlmHhZuzLJbtm84ZT4Keo6f2cCC74H_lx80EF_us8ujQNhEgzsiyTIhJKSFVNG6jlj", "sandbox");

        String retVal = "{}";
        try {
            payment.create(apiContext);
            String app_url = payment.getLinks().get(1).getHref();
            retVal = "{ \"result\": \"" + app_url + "\" }";
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return retVal;
    }



    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        APIContext apiContext = new APIContext("AS0d1AGRRklnaqv1c0CznnEXkG1YVl-9hGk5A_g7JRvFbLWpf1FUKlL9lI5h8mnI7zhoITm9Qm46W1Kg",
                "EBf5PEC8wEO_TpDUVSl5r3RnbaUJs34SHu6wx2aBZ0gQy3p2V27q2VpqL6o7A_Q9ZZLUDb84WpfIf-T5", "sandbox");
        return payment.execute(apiContext, paymentExecute);

    }
}
