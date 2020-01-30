package com.example.paypal.dto;

import lombok.Data;

@Data
public class SellerDto {
    private Long id;
    private String email;
    private String paypalEmail;
}
