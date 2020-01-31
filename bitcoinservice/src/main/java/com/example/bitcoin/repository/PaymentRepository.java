package com.example.bitcoin.repository;

import com.example.bitcoin.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(String orderId);
    List<Payment> getAllByStatus(String status);
}
