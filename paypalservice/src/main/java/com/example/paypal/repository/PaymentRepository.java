package com.example.paypal.repository;

import com.example.paypal.model.MyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<MyPayment,Long> {
    public MyPayment findByPaymentId(String paymentId);
    List<MyPayment> getAllByStatus(String status);
}