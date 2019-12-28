package com.example.demo.payment;


import com.example.demo.model.Payment;
import com.example.demo.card.CardDataDto;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    Payment findByUrl(String url);

    ExternalBankPaymentResponse handleKpRequest(ExternalBankPaymentRequest kpRequestDto);

    List<String> submitCardData(CardDataDto cardDataDto, String url);

}
