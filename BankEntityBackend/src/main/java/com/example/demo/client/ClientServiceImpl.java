package com.example.demo.client;

import com.example.demo.client.ClientService;
import com.example.demo.model.Client;
import com.example.demo.client.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findByMerchantId(String merchantId) { return clientRepository.findByMerchantId(merchantId); }

}
