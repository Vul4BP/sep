package com.example.paypal.repository;

import com.example.paypal.model.MyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<MyPayment,Long> {
    public MyPayment findByPaymentId(String paymentId);
}