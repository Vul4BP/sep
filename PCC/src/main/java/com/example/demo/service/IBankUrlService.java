package com.example.demo.service;

import com.example.demo.model.BankUrl;

public interface IBankUrlService {
    public BankUrl getOneByPanUniquePart(String panUniquePart);
}
