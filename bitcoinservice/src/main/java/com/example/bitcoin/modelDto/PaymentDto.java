package com.example.bitcoin.modelDto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentDto {
    Long paymentId;
    String apiToken;
    BigDecimal amount;
    String currency;
    String title;
    String successUrl;
    String cancelUrl;
}
