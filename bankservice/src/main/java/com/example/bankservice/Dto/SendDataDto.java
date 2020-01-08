package com.example.bankservice.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SendDataDto {
    private Long id;

    private String merchantId;

    private BigDecimal amount;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
