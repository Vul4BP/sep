package com.example.demo.payment;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ExternalBankPaymentRequest {
    private Long id;

    private String merchantId;

    private BigDecimal amount;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
