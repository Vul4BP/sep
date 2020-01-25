package com.example.paypal.dto;
import lombok.*;

@Data
public class CreatePaymentResponseDto {
    private String approvalUrl;
    private String id;
}

