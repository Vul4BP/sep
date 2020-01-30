package com.example.seller.service;

import com.example.seller.Dto.SellerDto;
import com.example.seller.models.Seller;

public interface ISellerService {
    public SellerDto addSeller(SellerDto sellerDto);
    public SellerDto getByEmail(String email);
    public Seller getByGeneratedId(String generatedId);
}
