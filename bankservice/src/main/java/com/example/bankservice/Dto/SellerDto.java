package com.example.bankservice.Dto;

import lombok.Data;

@Data
public class SellerDto {
    private Long id;
    private String email;
    private String merchantId;
    private Long magazineId;
}