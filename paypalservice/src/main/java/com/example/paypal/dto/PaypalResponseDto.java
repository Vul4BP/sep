package com.example.paypal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PaypalResponseDto {
    private String approvalUrl;
    private String PaymentId;
}


