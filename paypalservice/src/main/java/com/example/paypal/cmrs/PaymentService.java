package com.example.paypal.cmrs;

import com.example.paypal.dto.CreatePaymentRequest;
import com.example.paypal.dto.CreatePaymentResponse;
import com.example.paypal.dto.ExecutePaymentRequest;

public interface PaymentService {

    public abstract CreatePaymentResponse createPayment(CreatePaymentRequest kpRequest);

    public abstract String executePayment(ExecutePaymentRequest request);
}

