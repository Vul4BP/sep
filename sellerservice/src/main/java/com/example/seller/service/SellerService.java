package com.example.seller.service;

import com.example.seller.Dto.SellerDto;
import com.example.seller.models.NacinPlacanja;
import com.example.seller.models.Seller;
import com.example.seller.repo.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService implements ISellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public SellerDto addSeller(SellerDto sellerDto) {
        Seller seller = new Seller();

        seller.setEmail(sellerDto.getEmail());
        seller.setGeneratedId(sellerDto.getGeneratedId());
        seller.setNaciniPlacanja(sellerDto.getNaciniPlacanja());

        try {
            sellerRepository.save(seller);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        return sellerDto;
    }

    @Override
    public SellerDto getByEmail(String email) {
        Seller seller =  sellerRepository.findByEmail(email);
        if(seller == null){
            return null;
        }
        SellerDto dto = new SellerDto();
        dto.setEmail(seller.getEmail());
        dto.setGeneratedId(seller.getGeneratedId());
        dto.setNaciniPlacanja(seller.getNaciniPlacanja());
        return dto;
    }

    @Override
    public Seller getByGeneratedId(String generatedId) {
        return  sellerRepository.findByGeneratedId(generatedId);
    }
}
