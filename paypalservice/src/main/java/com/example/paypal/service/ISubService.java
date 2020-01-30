package com.example.paypal.service;

import com.example.paypal.dto.CreatePlanRequestDto;
import com.example.paypal.dto.SubscriptionRequestDto;
import com.paypal.api.payments.Plan;
import com.paypal.base.rest.PayPalRESTException;

import java.io.IOException;
import java.net.URL;

public interface ISubService {
    public Plan create(CreatePlanRequestDto request) throws PayPalRESTException, IOException;

    public URL subscribeToPlan(SubscriptionRequestDto request) throws PayPalRESTException;

    public void finishSubscription(String token);

    public void cancelSubscription(String token);
}
