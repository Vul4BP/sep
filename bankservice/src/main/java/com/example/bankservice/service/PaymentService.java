package com.example.bankservice.service;


import com.example.bankservice.Dto.CardDto;
import com.example.bankservice.Dto.PaymentRequestDto;
import com.example.bankservice.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();
    String useCardData(CardDto cardDto, String url);
    String handleRequest(PaymentRequestDto RequestDto);
    String changeStatus(String url, boolean status);
}
