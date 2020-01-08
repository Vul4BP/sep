package com.example.bankservice.service;

import com.example.bankservice.model.Client;
import java.util.List;

public interface ClientService {
    Client findByMerchantId(String merchantId);
    Client findByMagazineId(String magazineId);
    List<Client> findAll();
}
