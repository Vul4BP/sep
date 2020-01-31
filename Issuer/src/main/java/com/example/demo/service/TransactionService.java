package com.example.demo.service;


import com.example.demo.config.VarConfig;
import com.example.demo.dto.CardDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.*;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseDto persistPayment(RequestDto requestDto) {

        System.out.println("------ CITANJE PODATAKA SA KARTICE ------");
        CardDto cardDto = requestDto.getCardData();

        Transaction transaction = new Transaction();
        Client client = clientRepository.findByPayerId("00000000"); //sve transakcije dobijaju ovaj profil za primaoca sredstava
        if(client != null) {
            transaction.setMerchant(client.getAccount());  //profil platioca, znamo da je upravo ovaj, jer je kartica primaoca iz druge banke
        }
        transaction.setAmount(requestDto.getAmount()); //cena
        transaction.setTimestamp(generateTimeStamp());  //timestamp u issuer-u
        transaction.setOrderId(generateOrderId());  //orderId u issuer-u
        transaction.setPayer(null);     //kasnije ce se promenuti
        //id -> baza, status -> kasnije, payer -> kasnije

        Card card = cardRepository.findOneByPan(cardDto.getPan());
        if(card == null){
            return saveErrorTransaction(transaction);
        }

        if (!card.getSecurityCode().toString().equals(cardDto.getSecurityCode().toString())) {
            return saveErrorTransaction(transaction);
        }else if(!card.getValidTo().toString().equals(cardDto.getValidTo().toString())) {
            return saveErrorTransaction(transaction);
        }else if(!card.getHolderName().equals(cardDto.getHolderName())) {
            return saveErrorTransaction(transaction);
        }

        transaction.setPayer(card.getAccount());

        Account account = card.getAccount();

        if (account.getAmount().compareTo(transaction.getAmount()) < 0) {
            transaction.setStatus(VarConfig.paymentStatusFailed);
            System.out.println("------ TRANSAKCIJA -> FAILED ------");
        } else {
            account.setAmount(account.getAmount().subtract(transaction.getAmount()));
            accountRepository.save(account);

            transaction.setStatus(VarConfig.paymentStatusSuccess);
            System.out.println("------ TRANSAKCIJA -> SUCCESS ------");
        }
        transactionRepository.save(transaction);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(transaction.getStatus());
        responseDto.setIssuerTimestamp(transaction.getTimestamp());
        responseDto.setIssuerOrderId(transaction.getOrderId());

        return responseDto;
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

    private ResponseDto saveErrorTransaction(Transaction transaction){

        transaction.setStatus(VarConfig.paymentStatusError);
        transactionRepository.save(transaction);

        System.out.println("------ DOGODILA SE GRESKA ------");
        System.out.println("------ TRANSAKCIJA -> ERROR ------");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(transaction.getStatus());
        responseDto.setIssuerTimestamp(transaction.getTimestamp());
        responseDto.setIssuerOrderId(transaction.getOrderId());
        return responseDto;

    }
}

