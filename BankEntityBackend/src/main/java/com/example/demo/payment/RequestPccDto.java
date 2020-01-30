package com.example.demo.payment;

import com.example.demo.card.CardDataDto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class RequestPccDto {
    private CardDataDto cardData;
    private BigDecimal amount;
    private String orderId;
    private String timestamp;
}
