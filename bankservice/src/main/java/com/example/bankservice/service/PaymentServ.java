package com.example.bankservice.service;

import com.example.bankservice.Dto.PaymentRequestDto;
import com.example.bankservice.Dto.SellerDto;
import com.example.bankservice.Dto.SendDataDto;
import com.example.bankservice.config.VarConfig;
import com.example.bankservice.model.Payment;
import com.example.bankservice.model.Seller;
import com.example.bankservice.repository.PaymentRepository;
import com.example.bankservice.repository.SellerRepository;
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

        RestTemplate restTemplate = new RestTemplate();
        SendDataDto sendDto = new SendDataDto();
        SellerDto sellerDto = sellerService.findByEmail(requestDto.getEmail());

        sendDto.setMerchantId(sellerDto.getMerchantId());
        sendDto.setAmount(requestDto.getAmount());
        sendDto.setErrorUrl(VarConfig.paymentErrorUrl);
        sendDto.setFailedUrl(VarConfig.paymentFailedUrl);
        sendDto.setSuccessUrl(VarConfig.paymentSuccessUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SendDataDto> entity = new HttpEntity<SendDataDto>(sendDto, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(VarConfig.bankPaymentUrl, entity, String.class);

        Payment payment = new Payment();
        JSONObject actualObj = null;
        String urlStr = "";

        try {
            actualObj = new JSONObject(response.getBody());
            urlStr = actualObj.getString("url");
            payment.setUrl(urlStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Seller seller = sellerRepository.findByMerchantId(sellerDto.getMerchantId());
        payment.setSeller(seller);
        payment.setAmount(requestDto.getAmount());
        payment.setStatus(VarConfig.paymentStatusCreated);

        paymentRepository.save(payment);
        return VarConfig.bankFrontend + generateRedirectUrl(urlStr);
    }

    private String generateRedirectUrl(String url) {
        return "banka/card/" + url;
    }

    @Override
    public String changeStatus(String url, String status) {
        Payment payment = paymentRepository.findOneByUrl(url);
        payment.setStatus(status);
        paymentRepository.save(payment);
        return VarConfig.paymentRedirectUrl;
    }

    @Override
    public List<Payment> findAllByStatus(String status) {
        return paymentRepository.getAllByStatus(status);
    }
}