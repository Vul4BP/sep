package com.example.demo.client;

import com.example.demo.model.Client;

public interface ClientService {
    Client findByMerchantId(String merchantId);
}
