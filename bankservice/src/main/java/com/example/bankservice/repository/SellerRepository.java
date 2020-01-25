package com.example.bankservice.repository;

import com.example.bankservice.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByMerchantId(String merchantId);
    Seller findByMagazineId(Long id);
}
