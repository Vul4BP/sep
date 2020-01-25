package com.example.paypal.controller;

import com.example.paypal.dto.CreatePaymentRequestDto;
import com.example.paypal.dto.CreatePaymentResponseDto;
import com.example.paypal.dto.ExecutePaymentRequestDto;
import com.example.paypal.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaypalController {
    private final Logger LOGGER = LoggerFactory.getLogger(PaypalController.class);

    @Autowired
    PaymentService paymentService;

    @PostMapping("createPayment")
    public CreatePaymentResponseDto create(@RequestBody CreatePaymentRequestDto request) {
        return paymentService.createPayment(request);
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody ExecutePaymentRequestDto request) {
        return new ResponseEntity<String>(paymentService.executePayment(request), HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", paymentService.executePayment(request)).build();
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable("id") Long id)  {
        return new ResponseEntity<String>(paymentService.cancelPayment(id), HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", paymentService.cancelPayment(id)).build();
    }

    @GetMapping("/error/{id}")
    public ResponseEntity<String> errorPayment(@PathVariable("id") Long id)  {
        return new ResponseEntity<String>(paymentService.errorPayment(id), HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", paymentService.cancelPayment(id)).build();
    }

}
