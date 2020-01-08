package com.example.bitcoin.service;

import com.example.bitcoin.model.Seller;

public interface SellerService {
    Seller findByApiToken(String apiToken);
    Seller findByEmail(String email);
    Seller findByMagazineId(String magazineId);
}
