package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.BankUrl;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankUrlService bankUrlService;

    @Override
    public ResponseDto persistTransaction(String url, RequestDto requestDto) {

        System.out.println("------ CITANJE PODATAKA SA KARTICE ------");

        Transaction transaction = new Transaction();
        transaction.setAcquirerOrderId(requestDto.getOrderId());    //id transaikcije u acquirer-u
        transaction.setAcquirerTimestamp(requestDto.getTimestamp());  //timestamp transakcije u acquirer-u
        transaction.setAmount(requestDto.getAmount());  //cena
        transaction.setIssuerOrderId(null); //promenuce se kasnije
        transaction.setIssuerTimestamp(null);   //promenuce se kasnije
        //id -> baza, issuerOrderId -> kasnije, issuerTimestamp -> kasnije, status -> kasnije

        ResponseDto response = new ResponseDto();
        response.setAcquirerOrderId(transaction.getAcquirerOrderId());  //u odgovor se smesta id transakcije u acquirer-u
        response.setAcquirerTimestamp(transaction.getAcquirerTimestamp());  //u odgovor se smesta timestamp transakcije u acquirer-u
        //issuerOrderId -> kasnije, issuerTimestamp -> kasnije , status -> kasnije

        String panPart = requestDto.getCardData().getPan().substring(1, 7);   //provera na osnovu dela pana od drugog do sedmog karaktera
        //System.out.println("PanPart: \t" + panPart);

        BankUrl bankUrl = bankUrlService.getOneByPanUniquePart(panPart);
        if (bankUrl == null) {
            System.out.println("------ DOGODILA SE GRESKA ------");

            transaction.setStatus("Error"); //ne postoji banka platioca
            transactionRepository.save(transaction);

            response.setStatus(transaction.getStatus());
            return response;
        }

        String issuerRequestUrl = bankUrl.getRequestUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestDto> entity = new HttpEntity<RequestDto>(requestDto, headers);

        System.out.println("------ SALJE SE ZAHTEV KA ISSUER-u ------");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseDto> responseFromIssuer = restTemplate.postForEntity(issuerRequestUrl, entity, ResponseDto.class);
        if (responseFromIssuer.getStatusCode().isError()) {
            System.out.println("------ DOGODILA SE GRESKA ------");

            transaction.setStatus("Error"); //desila se neka greska
            transactionRepository.save(transaction);

            response.setStatus(transaction.getStatus());
            return response;
        }

        System.out.println("------ USPESNO DOBIJEN ODGOVOR OD ISSUER-a ------");
        ResponseDto responseBody = responseFromIssuer.getBody();
        //System.out.println(responseBody);

        transaction.setIssuerOrderId(responseBody.getIssuerOrderId());  //id transakcije u issuer-u
        transaction.setIssuerTimestamp(responseBody.getIssuerTimestamp()); //timestamp transakcije u issuer-u
        transaction.setStatus(responseBody.getStatus());    //status transakcije
        transactionRepository.save(transaction);

        response.setIssuerOrderId(responseBody.getIssuerOrderId()); //u odgovor se smesta id transakcije u banci platioca
        response.setIssuerTimestamp(responseBody.getIssuerTimestamp()); //u odgovor se smesta timestamp transakcije u banci platioca
        response.setStatus(responseBody.getStatus());   //u odgovor se smesta status

        return response;
    }
}
