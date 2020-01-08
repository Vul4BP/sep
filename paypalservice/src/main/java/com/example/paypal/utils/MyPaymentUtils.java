package com.example.paypal.utils;

import com.paypal.api.payments.*;

import java.math.BigDecimal;

public class MyPaymentUtils {

    private static final String CURRENCY="USD";
    private static final String RETURN_URL = "https://localhost:8433/paypalservice/api/paypal/success";
    private static final String CANCEL_URL = "https://localhost:8433/paypalservice/api/paypal/cancel/";


    public static Transaction setTransaction(BigDecimal total, String paypalEmail) {
        Amount amount = setAmount(total);

        Payee payee = setPayee(paypalEmail);

        Transaction transaction = new Transaction();

        transaction.setAmount(amount);
        transaction.setPayee(payee);

        return transaction;
    }

    public static Amount setAmount(BigDecimal total) {
        Amount amount = new Amount();

        amount.setCurrency(CURRENCY);
        amount.setTotal(total.toString());

        return amount;
    }

    public static Payee setPayee(String email) {
        Payee payee = new Payee();

        payee.setEmail(email);

        return payee;
    }

    public static Payer generatePayer(String method){
        Payer payer=new Payer();
        payer.setPaymentMethod(method);
        return payer;
    }

    public static RedirectUrls setRedirectUrls(Long id) {
        RedirectUrls redirectUrls = new RedirectUrls();

        redirectUrls.setReturnUrl(RETURN_URL);
        redirectUrls.setCancelUrl(CANCEL_URL + id);
        //redirectUrls.setCancelUrl(CANCEL_URL);

        return redirectUrls;
    }

}
