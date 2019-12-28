package com.example.demo.account;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {
    Account getOne(Long id);

    List<Account> findAll();

    Account save(Account account);
}
