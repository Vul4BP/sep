package com.example.bitcoin.service;

import com.example.bitcoin.model.Payment;
import com.example.bitcoin.modelDto.PaymentDto;
import com.example.bitcoin.modelDto.RequestDto;
import com.example.bitcoin.modelDto.ResponseDto;

public interface PaymentService {
    PaymentDto preparePayment(RequestDto requestDto);

    Payment persist(ResponseDto responseDto, PaymentDto paymentDto);

    Payment getById(Long id);

    Payment save(Payment payment);
}
