package com.example.paypal.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
public class CreatePaymentRequestDto {
    private Long magazineId;
    private BigDecimal amount;
}
