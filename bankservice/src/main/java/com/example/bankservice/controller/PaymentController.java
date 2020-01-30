package com.example.bankservice.controller;

import com.example.bankservice.Dto.CardDto;
import com.example.bankservice.Dto.PaymentRequestDto;
import com.example.bankservice.service.PaymentServ;
import com.example.bankservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentServ paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> paymentRequest(@RequestBody PaymentRequestDto requestDto) {

        String result = "{ \"paymentUrl\" : \"" + paymentService.handleRequest(requestDto) + "\" }";
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    public ResponseEntity<String> useCardData(@RequestBody CardDto cardDto, @PathVariable String url) {
        return new ResponseEntity<String>("\""+paymentService.useCardData(cardDto, url)+"\"", HttpStatus.OK);
    }*/

    @GetMapping("/success/{url}")
    public ResponseEntity<?> successPayment(@PathVariable String url) {
        LOGGER.info("Payment success");
        String redirect = paymentService.changeStatus(url,"Success");
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
        String body = "{ \"redirectUrl\" : \""+ redirect +"\" }";
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @GetMapping("/error/{url}")
    public ResponseEntity<?> errorPayment(@PathVariable String url) {
        LOGGER.info("Payment error");
        String redirect = paymentService.changeStatus(url,"Error");
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
        String body = "{ \"redirectUrl\" : \""+ redirect +"\" }";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/failed/{url}")
    public ResponseEntity<?> failedPayment(@PathVariable String url) {
        LOGGER.info("Payment failed");
        String redirect = paymentService.changeStatus(url,"Failed");
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
        String body = "{ \"redirectUrl\" : \""+ redirect +"\" }";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
