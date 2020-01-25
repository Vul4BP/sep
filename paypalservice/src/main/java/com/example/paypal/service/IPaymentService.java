package com.example.paypal.service;

import com.example.paypal.dto.CreatePaymentRequestDto;
import com.example.paypal.dto.CreatePaymentResponseDto;
import com.example.paypal.dto.ExecutePaymentRequestDto;

public interface IPaymentService {

    public abstract CreatePaymentResponseDto createPayment(CreatePaymentRequestDto kpRequest);

    public abstract String executePayment(ExecutePaymentRequestDto request);

    public abstract String cancelPayment(Long id);

    public abstract String errorPayment(Long id);
}


