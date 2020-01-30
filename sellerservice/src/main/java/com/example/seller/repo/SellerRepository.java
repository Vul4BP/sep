package com.example.seller.repo;

import com.example.seller.models.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, String> {
    Seller findByEmail(String email);
    Seller findByGeneratedId(String generatedId);
}
