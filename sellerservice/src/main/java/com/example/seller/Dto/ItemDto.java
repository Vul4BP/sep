package com.example.seller.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ItemDto {
    private String name;
    private Long izdanje;
    private String generatedId;
    private Long amount;
    private String sellerEmail;
}
