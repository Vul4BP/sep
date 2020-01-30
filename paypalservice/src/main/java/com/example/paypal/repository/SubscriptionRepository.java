package com.example.paypal.repository;

import com.example.paypal.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    public Subscription findSubscriptionByAgreementToken(String token);

}