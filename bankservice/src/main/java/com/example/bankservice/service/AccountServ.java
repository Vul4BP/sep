package com.example.bankservice.service;

import com.example.bankservice.model.Account;
import com.example.bankservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServ implements AccountService  {
    private final AccountRepository accountRepository;
    public AccountServ(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public Account getOne(Long id) {
        return accountRepository.getOne(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
