package com.example.paypal.dto;

import lombok.Data;

@Data
public class SubscriptionRequestDto {
    private String email;
    private String planId;
}
