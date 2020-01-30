package com.example.paypal.service;

import com.example.paypal.dto.SellerDto;
import com.example.paypal.model.Seller;
import com.example.paypal.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements ISellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public SellerDto findByEmail(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller == null){
            return null;
        }

        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setPaypalEmail(seller.getPaypalEmail());
        return sellerDto;
    }

    @Override
    public SellerDto addSeller(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setEmail(sellerDto.getEmail());
        seller.setPaypalEmail(sellerDto.getPaypalEmail());

        try {
            sellerRepository.save(seller);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        return sellerDto;
    }
}


