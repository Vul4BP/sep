package com.example.bitcoin.controller;

import com.example.bitcoin.model.Payment;
import com.example.bitcoin.modelDto.PaymentDto;
import com.example.bitcoin.modelDto.RequestDto;
import com.example.bitcoin.modelDto.ResponseDto;
import com.example.bitcoin.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class BitcoinController {
    private final Logger LOGGER = LoggerFactory.getLogger(BitcoinController.class);
    private final PaymentService paymentService;

    public BitcoinController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("preparePayment")
    public String postPreparePayment(RequestDto requestDto) {
        PaymentDto paymentDto = paymentService.preparePayment(requestDto);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + paymentDto.getApiToken());
        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("price_amount", paymentDto.getAmount().toString());
        bodyParams.add("price_currency", paymentDto.getCurrency());
        bodyParams.add("receive_currency", paymentDto.getCurrency());
        bodyParams.add("title", paymentDto.getTitle());
        bodyParams.add("success_url", paymentDto.getSuccessUrl());
        bodyParams.add("cancel_url", paymentDto.getCancelUrl());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyParams, headers);
        ResponseEntity<ResponseDto> response = restTemplate.postForEntity("https://api-sandbox.coingate.com/v2/orders", request, ResponseDto.class);
        String paymentUrl = Objects.requireNonNull(response.getBody()).getPaymentUrl();
        LOGGER.info("Payment URL: " + paymentUrl);
        paymentService.persist(response.getBody(), paymentDto);

        return "{ \"paymentUrl\" : \"" + paymentUrl + "\"} ";
    }

    @GetMapping("/success/{paymentId}")
    public ResponseEntity<?> getPaymentSuccess(@PathVariable Long paymentId) {
        LOGGER.info("Handling successful payment");
        return getRedirectUrl(paymentId);
    }

    @GetMapping("/cancel/{paymentId}")
    public ResponseEntity<?> getPaymentCanceled(@PathVariable Long paymentId) {
        LOGGER.info("Handling invalid payment");
        return getRedirectUrl(paymentId);
    }

    private ResponseEntity<?> getRedirectUrl(@PathVariable Long paymentId) {

        Payment payment = paymentService.getById(paymentId);
        LOGGER.info("Getting payment information..");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + payment.getSeller().getApiToken());
        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<ResponseDto> response = restTemplate.exchange("https://api-sandbox.coingate.com/v2/orders/" + payment.getOrderId(), HttpMethod.GET, request, ResponseDto.class);

        LOGGER.info("Basic payment info: " + Objects.requireNonNull(response.getBody()).toString());
        String status = response.getBody().getStatus();
        LOGGER.info("Changing payment status into: " + status);
        payment.setStatus(status);
        LOGGER.info("Persisting payment");
        Payment persistedPayment = paymentService.save(payment);
        LOGGER.info("Payment persisted: " + persistedPayment.toString());
        String redirectUrl = payment.getRedirectUrl();
        LOGGER.info("Redirecting to: " + redirectUrl);

        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
    }
}