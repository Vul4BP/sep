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
    public SellerDto findByMagazineId(Long magazineId) {
        Seller seller = sellerRepository.findByMagazineId(magazineId);
        if(seller == null){
            return null;
        }

        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setMagazineId(seller.getMagazineId());
        sellerDto.setClientId(seller.getClientId());
        sellerDto.setSecret(seller.getSecret());
        sellerDto.setEmail(seller.getEmail());
        return sellerDto;
    }

    @Override
    public SellerDto addSeller(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setClientId(sellerDto.getClientId());
        seller.setSecret(sellerDto.getSecret());
        seller.setEmail(sellerDto.getEmail());
        seller.setMagazineId(sellerDto.getMagazineId());

        try {
            sellerRepository.save(seller);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        return sellerDto;
    }
}


