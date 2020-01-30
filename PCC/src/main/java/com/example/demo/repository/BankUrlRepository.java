package com.example.demo.repository;

import com.example.demo.model.BankUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUrlRepository extends JpaRepository<BankUrl, Long> {
    public BankUrl findByPanUniquePart(String panUniquePart);
}
