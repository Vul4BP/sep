package com.example.demo.payment;

import lombok.Data;

@Data
public class ResponsePccDto {
    private String status;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;
}
