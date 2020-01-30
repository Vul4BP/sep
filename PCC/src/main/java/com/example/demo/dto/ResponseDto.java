package com.example.demo.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String status;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;
}
