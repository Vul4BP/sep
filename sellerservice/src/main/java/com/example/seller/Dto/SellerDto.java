package com.example.seller.Dto;

import com.example.seller.models.NacinPlacanja;
import lombok.Data;

import java.util.List;

@Data
public class SellerDto {
    private Long id;
    private String email;
    private String generatedId;
    private List<NacinPlacanja> naciniPlacanja;
}
