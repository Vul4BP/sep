package com.example.bankservice.schedulingtasks;

import com.example.bankservice.Dto.SendDataDto;
import com.example.bankservice.config.VarConfig;
import com.example.bankservice.model.Payment;
import com.example.bankservice.repository.PaymentRepository;
import com.example.bankservice.service.PaymentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CheckPaymentsTask {
    @Autowired
    private PaymentServ paymentServ;

    @Autowired
    private PaymentRepository paymentRepository;

    @Scheduled(fixedRate = 60000)   //na 1 min
    public void checkPayments() {
        getCurrentTimeUsingDate();
        List<Payment> createdPayments = paymentServ.findAllByStatus(VarConfig.paymentStatusCreated);
        for(Payment p : createdPayments){
            String message = "Payment: " + p.getUrl() + " \t " + p.getStatus() + "\t->\t";
            String requestUrl = VarConfig.bankPaymentUrl + "/get/" + p.getUrl();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
            if(response.getBody() != null) {
                p.setStatus(response.getBody());
                paymentRepository.save(p);
                message += p.getStatus();
                System.out.println(message);
            }
        }
        System.out.println("--------------------------------------------------\n");
    }

    public static void getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        System.out.println("-------------------- " + formattedDate + " --------------------");
    }
}
