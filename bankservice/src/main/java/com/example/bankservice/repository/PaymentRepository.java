package com.example.bankservice.repository;

import com.example.bankservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findOneByUrl(String url);
    List<Payment> getAllByStatus(String status);
}
