package com.example.bankservice.service;

import com.example.bankservice.Dto.CardDto;
import com.example.bankservice.Dto.PaymentRequestDto;
import com.example.bankservice.Dto.SellerDto;
import com.example.bankservice.Dto.SendDataDto;
import com.example.bankservice.config.VarConfig;
import com.example.bankservice.model.Payment;
import com.example.bankservice.model.Seller;
import com.example.bankservice.repository.PaymentRepository;
import com.example.bankservice.repository.SellerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentServ implements PaymentService {

    private static final String PAYMENT_URL_F = "%s/card/%s";
    public static final String NOT_FOUND = "notFound";

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public String handleRequest(PaymentRequestDto requestDto) {

        Payment payment = new Payment();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        SendDataDto sendDto = new SendDataDto();
        sendDto.setAmount(requestDto.getAmount());
        sendDto.setErrorUrl(VarConfig.paymentErrorUrl);
        sendDto.setFailedUrl(VarConfig.paymentCancelUrl);
        sendDto.setSuccessUrl(VarConfig.paymentSuccessUrl);

        Long magId = Long.parseLong(requestDto.getMagazineId());
        SellerDto sellerDto = sellerService.findByMagazineId(magId);
        sendDto.setMerchantId(sellerDto.getMerchantId());

        String body = "";
        try {
            body = mapper.writeValueAsString(sendDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(VarConfig.bankPaymentUrl, entity, String.class);

        JSONObject actualObj = null;
        String urlStr = "";

        try {
            actualObj = new JSONObject(response.getBody());
            urlStr = actualObj.getString("url");
            payment.setUrl(urlStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        payment.setAmount(requestDto.getAmount());

        Seller seller = sellerRepository.findByMerchantId(sellerDto.getMerchantId());
        payment.setSeller(seller);

        paymentRepository.save(payment);
        return VarConfig.bankFrontend + urlStr;

    }

    @Override
    public String useCardData(CardDto cardDto, String url) {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = VarConfig.bankPaymentUrl + url;

        ObjectMapper mapper = new ObjectMapper();
        CardDto cd = new CardDto();

        cd.setPan(cardDto.getPan());
        cd.setHolderName(cardDto.getHolderName());
        cd.setSecurityCode(cardDto.getSecurityCode());
        cd.setValidTo(cardDto.getValidTo());

        String body = "";
        try {
            body = mapper.writeValueAsString(cd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, entity, String.class);
        System.out.println(response.getBody());

        JSONObject actualObj = null;
        String urlStr = "";
        String placenoStr = "";

        try {
            actualObj = new JSONObject(response.getBody());
            urlStr = actualObj.getString("url");
            placenoStr = actualObj.getString("placeno");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Payment payment = paymentRepository.findOneByUrl("banka/card/" + url);
        Payment savedPayment = new Payment();

        if (placenoStr.equals("true")) {
            payment.setStatus("Success");
        } else {
            payment.setStatus("Canceled");
        }

        savedPayment = paymentRepository.save(payment);

        return urlStr;
    }

    @Override
    public String changeStatus(String url, String status) {
        Payment payment = paymentRepository.findOneByUrl("banka/card/" + url);
        payment.setStatus(status);
        paymentRepository.save(payment);
        return VarConfig.paymentRedirectUrl;
    }
}