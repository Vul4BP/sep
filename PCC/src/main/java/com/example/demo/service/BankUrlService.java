package com.example.demo.service;

import com.example.demo.model.BankUrl;
import com.example.demo.repository.BankUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankUrlService implements IBankUrlService{
    @Autowired
    private BankUrlRepository bankUrlRepository;

    @Override
    public BankUrl getOneByPanUniquePart(String panUniquePart) {
        return bankUrlRepository.findByPanUniquePart(panUniquePart);
    }
}
