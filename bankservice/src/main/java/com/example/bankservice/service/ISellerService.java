package com.example.bankservice.service;

import com.example.bankservice.Dto.SellerDto;
import com.example.bankservice.model.Seller;

import java.util.List;

public interface ISellerService {
    SellerDto findByEmail(String email);
    SellerDto addSeller(SellerDto sellerDto);
    Seller findByMerchantId(String merchantId);
    List<Seller> findAll();
}
