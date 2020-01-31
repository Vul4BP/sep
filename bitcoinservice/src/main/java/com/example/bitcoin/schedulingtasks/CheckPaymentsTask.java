package com.example.bitcoin.schedulingtasks;

import com.example.bitcoin.config.VarConfig;
import com.example.bitcoin.model.Payment;
import com.example.bitcoin.modelDto.ResponseDto;
import com.example.bitcoin.repository.PaymentRepository;
import com.example.bitcoin.service.PaymentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
            String message = "Payment: " + p.getId() + " \t " + p.getStatus() + "\t->\t";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Token " + p.getSeller().getApiToken());
            HttpEntity<String> request = new HttpEntity<>("parameters", headers);
            ResponseEntity<ResponseDto> response = restTemplate.exchange(VarConfig.coingateUrl + "/" + p.getOrderId(), HttpMethod.GET, request, ResponseDto.class);
            if(response.getBody() != null) {
                p.setStatus(response.getBody().getStatus());
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
