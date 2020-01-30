package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDto {
    private CardDto cardData;
    private BigDecimal amount;
    private String orderId;
    private String timestamp;
}
