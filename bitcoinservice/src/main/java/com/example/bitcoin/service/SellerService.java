package com.example.bitcoin.service;

import com.example.bitcoin.model.Seller;
import com.example.bitcoin.modelDto.SellerDto;
import com.example.bitcoin.repository.SellerRepository;
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
        sellerDto.setApiToken(seller.getApiToken());
        sellerDto.setEmail(seller.getEmail());
        return sellerDto;
    }

    @Override
    public SellerDto addSeller(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setApiToken(sellerDto.getApiToken());
        seller.setEmail(sellerDto.getEmail());

        try {
            sellerRepository.save(seller);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        return sellerDto;
    }

    @Override
    public Seller findByApiToken(String apiToken) {
        return sellerRepository.findByApiToken(apiToken);
    }
}

