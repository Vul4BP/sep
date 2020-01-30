package com.example.bitcoin.service;
import com.example.bitcoin.model.Seller;
import com.example.bitcoin.modelDto.SellerDto;

public interface ISellerService {
    SellerDto addSeller(SellerDto sellerDto);
    Seller findByApiToken(String apiToken);
    SellerDto findByEmail(String email);
}

