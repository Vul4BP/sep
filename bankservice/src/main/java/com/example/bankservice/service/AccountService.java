package com.example.bankservice.service;

import com.example.bankservice.model.Account;

import java.util.List;

public interface AccountService {
    Account getOne(Long id);
    List<Account> findAll();
    Account save(Account account);
}
