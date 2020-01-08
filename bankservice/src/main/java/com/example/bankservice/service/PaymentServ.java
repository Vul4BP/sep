package com.example.bankservice.service;

import com.example.bankservice.Dto.CardDto;
import com.example.bankservice.Dto.PaymentRequestDto;
import com.example.bankservice.Dto.PaymentResponseDto;
import com.example.bankservice.Dto.SendDataDto;
import com.example.bankservice.model.Client;
import com.example.bankservice.model.Payment;
import com.example.bankservice.repository.ClientRepository;
import com.example.bankservice.repository.PaymentRepository;
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
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public String handleRequest(PaymentRequestDto requestDto) {

        Payment payment = new Payment();
        Payment savedPayment;

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8091/payment";

        ObjectMapper mapper = new ObjectMapper();
        SendDataDto sd = new SendDataDto();
        sd.setAmount(requestDto.getAmount());
        sd.setErrorUrl("https://localhost:8443/bankservice/payment/error/");
        sd.setFailedUrl("https://localhost:8443/bankservice/payment/failed/");
        sd.setSuccessUrl("https://localhost:8443/bankservice/payment/success/");
        Client cl = clientRepository.findByMagazineId(requestDto.getMagazineId());
        sd.setMerchantId(cl.getMerchantId());

        String body = "";
        try {
            body = mapper.writeValueAsString(sd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, entity, String.class);

        JSONObject actualObj = null;
        String ret = "";

        try {
            actualObj = new JSONObject(response.getBody());
            ret = actualObj.getString("url");
            payment.setUrl(ret);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        payment.setMagazineId(requestDto.getMagazineId());
        payment.setAmount(requestDto.getAmount());
        payment.setPaid(false);

        savedPayment = paymentRepository.save(payment);
        return "http://localhost:5010/" + ret;

    }

    @Override
    public String useCardData(CardDto cardDto, String url) {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8091/payment/" + url;

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
        String ret = "";
        String ret1 = "";


        try {
            actualObj = new JSONObject(response.getBody());
            ret = actualObj.getString("url");
            ret1 = actualObj.getString("placeno");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Payment payment = paymentRepository.findOneByUrl("banka/card/" + url);
        Payment savedPayment = new Payment();

        if (ret1.equals("true")) {
            payment.setPaid(true);
        } else {
            payment.setPaid(false);
        }

        savedPayment = paymentRepository.save(payment);

        return ret;
    }

    @Override
    public String changeStatus(String url,boolean status) {
        Payment payment = paymentRepository.findOneByUrl("banka/card/" + url);
        payment.setPaid(status);
        paymentRepository.save(payment);
        return "https://localhost:5004";
    }
}