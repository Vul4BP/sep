package com.example.bitcoin.repository;

import com.example.bitcoin.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
    Seller findByApiToken(String apiToken);
    Seller findByMagazineId(Long magazineId);
}
