package com.example.paypal.service;

import com.example.paypal.dto.SellerDto;
import com.example.paypal.model.Seller;

public interface ISellerService {
    SellerDto findByMagazineId(Long magazineId);
    SellerDto addSeller(SellerDto sellerDto);
}
