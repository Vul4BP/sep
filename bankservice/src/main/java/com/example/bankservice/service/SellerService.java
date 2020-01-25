package com.example.bankservice.service;

import com.example.bankservice.Dto.SellerDto;
import com.example.bankservice.model.Seller;
import com.example.bankservice.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        sellerDto.setMerchantId(seller.getMerchantId());
        sellerDto.setEmail(seller.getEmail());
        return sellerDto;
    }

    @Override
    public SellerDto addSeller(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setMerchantId(sellerDto.getMerchantId());
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

    @Override
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller findByMerchantId(String merchantId) {
        return sellerRepository.findByMerchantId(merchantId);
    }

}
