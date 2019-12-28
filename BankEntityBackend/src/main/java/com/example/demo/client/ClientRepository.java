package com.example.demo.client;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByMerchantId(String merchantId);
}
