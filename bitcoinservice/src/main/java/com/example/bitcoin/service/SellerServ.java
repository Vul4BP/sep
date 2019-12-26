package com.example.bitcoin.service;

import com.example.bitcoin.model.Payment;
import com.example.bitcoin.model.Seller;
import com.example.bitcoin.modelDto.PaymentDto;
import com.example.bitcoin.modelDto.RequestDto;
import com.example.bitcoin.repository.PaymentRepository;
import com.example.bitcoin.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SellerServ implements SellerService {
    private final SellerRepository sellerRepository;

    public SellerServ(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller findByEmail(String email) {
        return sellerRepository.findByEmail(email);
    }

    @Override
    public Seller findByApiToken(String apiToken) {
        return sellerRepository.findByApiToken(apiToken);
    }
}

