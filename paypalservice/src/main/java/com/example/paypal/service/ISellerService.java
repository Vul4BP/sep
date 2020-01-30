package com.example.paypal.service;

import com.example.paypal.dto.SellerDto;
import com.example.paypal.model.Seller;

public interface ISellerService {
    SellerDto findByEmail(String email);
    SellerDto addSeller(SellerDto sellerDto);
}
