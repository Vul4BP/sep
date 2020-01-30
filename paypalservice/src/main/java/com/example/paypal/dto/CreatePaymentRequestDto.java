package com.example.paypal.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
public class CreatePaymentRequestDto {
    private String email;
    private BigDecimal amount;
}
