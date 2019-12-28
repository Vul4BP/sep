package com.example.demo.client;

import com.example.demo.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findByMerchantId(String merchantId);
}
