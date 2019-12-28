package com.example.bankservice.repository;

import com.example.bankservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByMerchantId(String merchantId);
    Client findByMagazineId(String id);
}
