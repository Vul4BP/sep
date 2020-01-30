package com.example.paypal.repository;

import com.example.paypal.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    public Seller findByEmail(String email);
}
