package com.example.demo.payment;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByUrl(String url);
}
