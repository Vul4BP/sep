package com.example.bankservice.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequestDto {
    private String magazineId;
    private BigDecimal amount;
}
