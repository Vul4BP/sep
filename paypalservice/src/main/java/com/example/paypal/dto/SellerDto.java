package com.example.paypal.dto;

import lombok.Data;

@Data
public class SellerDto {
    private Long id;
    private String email;
    private String clientId;
    private String secret;
    private Long magazineId;
}
