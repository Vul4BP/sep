package com.example.demo.payment;

import com.example.demo.account.AccountService;
import com.example.demo.card.CardService;
import com.example.demo.client.ClientService;
import com.example.demo.model.*;
import com.example.demo.transaction.TransactionRepository;
import com.example.demo.transaction.TransactionService;
import com.example.demo.card.CardDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CardService cardService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ExternalBankPaymentResponse handleKpRequest(ExternalBankPaymentRequest kpRequestDto) {

        System.out.println("------ GENERISANJE ZAHTEVA ------");

        Payment payment = new Payment();
        ExternalBankPaymentResponse response = new ExternalBankPaymentResponse();

        Client client = clientService.findByMerchantId(kpRequestDto.getMerchantId());
        if (client == null) {
            System.out.println("------ ZAHTEV NIJE IZGENERISAN ------");
            return null;
        } else {
            payment.setUrl(generateUrl());    //generisanje url-a
            payment.setAmount(kpRequestDto.getAmount());    //cena
            payment.setSuccessUrl(kpRequestDto.getSuccessUrl());    //uspesno
            payment.setFailedUrl(kpRequestDto.getFailedUrl());  //neuspesno
            payment.setErrorUrl(kpRequestDto.getErrorUrl());    //greska
            payment.setMerchantAccount(client.getAccount());    //profil primaoca sredstava
            //id -> baza, status -> kasnije

            paymentRepository.save(payment);
            System.out.println("------ ZAHTEV JE USPESNO IZGENERISAN ------");
        }

        response.setUrl(generateRedirectUrl(payment.getUrl()));
        response.setId(payment.getId());

        return response;
    }

    @Override
    public List<String> submitCardData(CardDataDto cardDataDto, String url) {

        System.out.println("------ CITANJE PODATAKA SA KARTICE ------");

        List<String> list = new ArrayList<String>();
        String redirectUrl = "";

        Payment payment = paymentRepository.findByUrl(url);
        //System.out.println("Id: \t" + payment.getId());
        //System.out.println("Url: \t" + payment.getUrl());

        Transaction transaction = new Transaction();
        transaction.setMerchant(payment.getMerchantAccount());  //profil primaoca sredstava
        transaction.setAmount(payment.getAmount()); //cena
        transaction.setTimestamp(generateTimeStamp());  //timestamp
        transaction.setOrderId(generateOrderId());  //orderId
        transaction.setPayer(null);     //kasnije ce se promenuti
        //id -> baza, status -> kasnije, payer -> kasnije

        Card card = cardService.findByPan(cardDataDto.getPan());

        if (card == null) {
            System.out.println("------ KARTICA SE NE NALAZI U ACQUIRER-u ------");
            System.out.println("------ SALJE SE ZAHTEV KA PCC-u ------");
            //KARTICA KUPCA SE NE NALAZI U ISTOJ BANCI, SLEDI OBRACANJE PCC-u

            Client client = clientService.findByMerchantId("00000000"); //sve transakcije ciji platioc je van ove banke dobijaju ovaj profil platioca sredstava
            if(client != null) {
                transaction.setPayer(client.getAccount());  //profil platioca, znamo da je upravo ovaj, jer je kartica platioca iz neke druge banke
            }

            String pccRequestUrl = "http://localhost:8092/transaction/" + payment.getUrl();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RequestPccDto requestPccDto = new RequestPccDto();  //kreira se objekat koji ce se postovati pcc-u
            requestPccDto.setCardData(cardDataDto);
            requestPccDto.setAmount(transaction.getAmount());
            requestPccDto.setOrderId(transaction.getOrderId());
            requestPccDto.setTimestamp(transaction.getTimestamp());

            HttpEntity<RequestPccDto> entity = new HttpEntity<RequestPccDto>(requestPccDto, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ResponsePccDto> response = restTemplate.postForEntity(pccRequestUrl, entity, ResponsePccDto.class);
            if(response.getStatusCode().isError()){
                return saveErrorTransaction(payment, transaction);   //desila se neka greska
            }

            System.out.println("------ USPESNO DOBIJEN ODGOVOR OD PCC-a ------");
            ResponsePccDto responseBody = response.getBody();
            //System.out.println(responseBody);

            if(responseBody.getStatus().equals("Success")) {
                System.out.println("------ TRANSAKCIJA -> SUCCESS ------");
                Account merchant = payment.getMerchantAccount();
                merchant.setAmount(merchant.getAmount().add(payment.getAmount()));
                accountService.save(merchant);

                transaction.setStatus(responseBody.getStatus());
                payment.setStatus(responseBody.getStatus());    //status paymenta dobijen u odgovoru
                redirectUrl = payment.getSuccessUrl();
            }else{
                transaction.setStatus(responseBody.getStatus());
                payment.setStatus(responseBody.getStatus());

                if(payment.getStatus().equals("Error")){
                    System.out.println("------ TRANSAKCIJA -> ERROR ------");
                    redirectUrl = payment.getErrorUrl();
                }else{
                    System.out.println("------ TRANSAKCIJA -> FAILED ------");
                    redirectUrl = payment.getFailedUrl();
                }
            }

            transactionRepository.save(transaction);
            paymentRepository.save(payment);

            list.add(redirectUrl);
            list.add("true");
            return list;
        }

        System.out.println("------ KARTICA SE NALAZI U ACQUIRER-u ------");
        //KARTICA KUPCA SE NALAZI U ISTOJ BANCI, PROVERAVAJU SE OSTALI PODACI
        //System.out.println("Pan: \t" + cardDataDto.getPan());
        //System.out.println("Holder: \t" + card.getHolderName());

        if (!card.getSecurityCode().toString().equals(cardDataDto.getSecurityCode().toString())) {
            return saveErrorTransaction(payment, transaction);
        }else if(!card.getValidTo().toString().equals(cardDataDto.getValidTo().toString())) {
            return saveErrorTransaction(payment, transaction);
        }else if(!card.getHolderName().equals(cardDataDto.getHolderName())) {
            return saveErrorTransaction(payment, transaction);
        }

        transaction.setPayer(card.getAccount());

        Account merchant = payment.getMerchantAccount();
        Account account = card.getAccount();

        if (account.getAmount().compareTo(payment.getAmount()) < 0) {
            transaction.setStatus("Failed");
            payment.setStatus(transaction.getStatus());
            redirectUrl = payment.getFailedUrl();
            System.out.println("------ TRANSAKCIJA -> FAILED ------");

        } else {
            account.setAmount(account.getAmount().subtract(payment.getAmount()));   //skidanje para sa jednog acc
            merchant.setAmount(merchant.getAmount().add(payment.getAmount()));  //dodavanje para na drugi

            accountService.save(account);
            accountService.save(merchant);

            transaction.setStatus("Success");
            payment.setStatus(transaction.getStatus());
            redirectUrl = payment.getSuccessUrl();
            System.out.println("------ TRANSAKCIJA -> SUCCESS ------");
        }

        transactionRepository.save(transaction);
        paymentRepository.save(payment);

        list.add(redirectUrl);
        list.add("true");
        return list;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findByUrl(String url) {
        return paymentRepository.findByUrl(url);
    }

    private String generateUrl() {
        return String.valueOf(System.currentTimeMillis() + ThreadLocalRandom.current().nextInt(10, 99));
    }

    private String generateOrderId(){
        String num = "";
        for(int i=0; i<10; i++){
            num += String.valueOf(ThreadLocalRandom.current().nextInt(10));
        }
        return num;
    }

    private String generateTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String generateRedirectUrl(String url) {
        return "banka/card/" + url;
    }

    private List<String> saveErrorTransaction(Payment payment, Transaction transaction){
        List<String> list = new ArrayList<>();

        transaction.setStatus("Error");
        transactionRepository.save(transaction);

        payment.setStatus(transaction.getStatus());
        paymentRepository.save(payment);

        System.out.println("------ DOGODILA SE GRESKA ------");
        System.out.println("------ TRANSAKCIJA -> ERROR ------");

        list.add(payment.getErrorUrl());
        list.add("false");
        return list;
    }
}