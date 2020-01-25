package com.example.bitcoin.service;
import com.example.bitcoin.model.Seller;
import com.example.bitcoin.modelDto.SellerDto;

public interface ISellerService {
    SellerDto findByMagazineId(Long magazineId);
    SellerDto addSeller(SellerDto sellerDto);
    Seller findByApiToken(String apiToken);
    Seller findByEmail(String email);
}

