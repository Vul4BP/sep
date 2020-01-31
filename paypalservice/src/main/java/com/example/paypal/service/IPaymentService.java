package com.example.paypal.service;

import com.example.paypal.dto.CreatePaymentRequestDto;
import com.example.paypal.dto.CreatePaymentResponseDto;
import com.example.paypal.dto.ExecutePaymentRequestDto;
import com.example.paypal.model.MyPayment;

import java.util.List;

public interface IPaymentService {

    public abstract CreatePaymentResponseDto createPayment(CreatePaymentRequestDto kpRequest);
    public abstract String executePayment(ExecutePaymentRequestDto request);
    public abstract String cancelPayment(Long id);
    public abstract String errorPayment(Long id);
    List<MyPayment> findAllByStatus(String status);
}


