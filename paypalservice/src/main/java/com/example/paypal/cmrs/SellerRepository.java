package com.example.paypal.cmrs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    public Seller findByMagazineID(String id);

}
