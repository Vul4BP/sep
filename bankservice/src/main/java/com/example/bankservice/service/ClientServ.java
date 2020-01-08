package com.example.bankservice.service;

import com.example.bankservice.model.Client;
import com.example.bankservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServ implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServ(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByMerchantId(String merchantId) {
        return clientRepository.findByMerchantId(merchantId);
    }

    @Override
    public Client findByMagazineId(String magazineId) {
        return clientRepository.findByMagazineId(magazineId);
    }
}
