package com.example.paypal.dto;
import lombok.Data;

@Data
public class ExecutePaymentRequestDto {
    private String paymentId;
    private String payerId;
}

