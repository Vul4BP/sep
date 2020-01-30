package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> postCardData(@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(transactionService.persistPayment(requestDto), HttpStatus.OK);
    }
}
