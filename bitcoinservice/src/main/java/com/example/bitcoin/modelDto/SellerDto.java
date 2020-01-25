package com.example.bitcoin.modelDto;

import lombok.Data;

@Data
public class SellerDto {
    private Long id;
    private String email;
    private String apiToken;
    private Long magazineId;
}
