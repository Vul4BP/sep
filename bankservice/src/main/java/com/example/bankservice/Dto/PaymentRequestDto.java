package com.example.bankservice.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequestDto {
    private String email;
    private BigDecimal amount;
}
