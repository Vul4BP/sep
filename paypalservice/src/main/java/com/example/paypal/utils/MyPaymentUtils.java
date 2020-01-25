package com.example.paypal.utils;

import com.example.paypal.config.VarConfig;
import com.paypal.api.payments.*;
import org.aspectj.weaver.ast.Var;

import java.math.BigDecimal;

public class MyPaymentUtils {

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
        amount.setCurrency(VarConfig.paymentCurrency);
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
        redirectUrls.setReturnUrl(VarConfig.paymentReturnUrl);
        redirectUrls.setCancelUrl(VarConfig.paymentCancelUrl + id);

        return redirectUrls;
    }
}
