package com.example.paypal.service;

import com.example.paypal.config.VarConfig;
import com.example.paypal.dto.CreatePlanRequestDto;
import com.example.paypal.dto.SubscriptionRequestDto;
import com.example.paypal.model.Seller;
import com.example.paypal.model.Subscription;
import com.example.paypal.repository.SellerRepository;
import com.example.paypal.repository.SubscriptionRepository;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubService implements ISubService{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Plan create(CreatePlanRequestDto request) throws PayPalRESTException, IOException {

        APIContext apiContext = new APIContext(VarConfig.clinetId, VarConfig.secret, VarConfig.mode);

        // Build Plan object
        Plan plan = new Plan();
        plan.setName(request.getNameOfJournal());
        plan.setDescription(request.getDescription());
        plan.setType("fixed");

        //payment_definitions
        PaymentDefinition paymentDefinition = new PaymentDefinition();
        paymentDefinition.setName("Regular Payments");
        paymentDefinition.setType(request.getTypeOfPlan().name());
        paymentDefinition.setFrequency(request.getFrequencyPayment().name());
        paymentDefinition.setFrequencyInterval(String.valueOf(request.getFrequencyInterval()));
        paymentDefinition.setCycles(String.valueOf(request.getCycles()));

        //currency
        Currency currency = new Currency();
        currency.setCurrency(request.getCurrency());
        currency.setValue(String.valueOf(request.getPrice()));
        paymentDefinition.setAmount(currency);

        //charge_models
        ChargeModels chargeModels = new com.paypal.api.payments.ChargeModels();
        chargeModels.setType("SHIPPING");
        chargeModels.setAmount(currency);
        List<ChargeModels> chargeModelsList = new ArrayList<ChargeModels>();
        chargeModelsList.add(chargeModels);
        paymentDefinition.setChargeModels(chargeModelsList);

        //payment_definition
        List<PaymentDefinition> paymentDefinitionList = new ArrayList<PaymentDefinition>();
        paymentDefinitionList.add(paymentDefinition);
        plan.setPaymentDefinitions(paymentDefinitionList);

        //merchant_preferences
        MerchantPreferences merchantPreferences = new MerchantPreferences();
        merchantPreferences.setSetupFee(currency);
        merchantPreferences.setCancelUrl("https://localhost:8443/paypalservice/subscription/plan/cancelSubscription");
        merchantPreferences.setReturnUrl("https://localhost:8443/paypalservice/subscription/plan/finishSubscription");
        merchantPreferences.setMaxFailAttempts("0");
        merchantPreferences.setAutoBillAmount("YES");
        merchantPreferences.setInitialFailAmountAction("CONTINUE");
        plan.setMerchantPreferences(merchantPreferences);

        Plan createdPlan = plan.create(apiContext);

        List<Patch> patchRequestList = new ArrayList<Patch>();
        Map<String, String> value = new HashMap<String, String>();
        value.put("state", "ACTIVE");

        Patch patch = new Patch();
        patch.setPath("/");
        patch.setValue(value);
        patch.setOp("replace");
        patchRequestList.add(patch);

        Plan newPlan = Plan.get(apiContext, createdPlan.getId());

        newPlan.update(apiContext, patchRequestList);

        Plan activatedPlan =  Plan.get(apiContext, newPlan.getId());
        return activatedPlan;
    }

    @Override
    public URL subscribeToPlan(SubscriptionRequestDto request) throws PayPalRESTException {

        APIContext apiContext = new APIContext(VarConfig.clinetId, VarConfig.secret, VarConfig.mode);

        // Create new agreement
        Agreement agreement = new Agreement();
        agreement.setName("Base Agreement");
        agreement.setDescription("Basic Agreement");
        agreement.setStartDate("2020-10-10T9:45:04Z");

        Plan createdPlan = Plan.get(apiContext, request.getPlanId());
        Plan plan  = new Plan();
        plan.setId(createdPlan.getId());
        agreement.setPlan(plan);

        Subscription sub = new Subscription();
        sub.setPlanId(createdPlan.getId());
        PaymentDefinition pd = createdPlan.getPaymentDefinitions().get(0);
        sub.setAmount(pd.getAmount().getValue());

        sub.setFrequency(pd.getFrequency());
        sub.setFrequencyInterval(pd.getFrequencyInterval());
        sub.setCycles(pd.getCycles());
        sub.setStatus("");
        Seller seller = sellerRepository.findByEmail(request.getEmail());

        if(seller!=null)
            sub.setSeller(seller);

        //subscriptionRepository.save(sub);

        // Add payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        agreement.setPayer(payer);

        // Set shipping address information
        ShippingAddress shipping = new ShippingAddress();
        shipping.setLine1("111 First Street");
        shipping.setCity("Saratoga");
        shipping.setState("CA");
        shipping.setPostalCode("95070");
        shipping.setCountryCode("US");
        agreement.setShippingAddress(shipping);

        // Create agreement
        try {
            agreement = agreement.create(apiContext);

            sub.setAgreementToken(agreement.getToken());
            subscriptionRepository.save(sub);

            for (Links links : agreement.getLinks()) {
                if ("approval_url".equals(links.getRel())) {
                    URL url = new URL(links.getHref());

                    //REDIRECT USER TO url
                    return url;
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void finishSubscription(String token) {

        APIContext apiContext = new APIContext(VarConfig.clinetId, VarConfig.secret, VarConfig.mode);

        Agreement agreement = new Agreement();
        agreement.setToken(token);
        Subscription sub = new Subscription();

        try {
            Agreement activeAgreement = agreement.execute(apiContext, agreement.getToken());
            sub = subscriptionRepository.findSubscriptionByAgreementToken(token);
            sub.setStatus("SUCCESS");
            subscriptionRepository.save(sub);
            //log.info("Agreement created with ID {}", activeAgreement.getId());
        } catch (PayPalRESTException e) {
            //  log.error(e.getDetails().getMessage());
        }
    }

    @Override
    public void cancelSubscription(String token) {
        Subscription sub = new Subscription();

        sub = subscriptionRepository.findSubscriptionByAgreementToken(token);
        System.out.println(sub);
        sub.setStatus("CANCEL");
        subscriptionRepository.save(sub);
    }
}
