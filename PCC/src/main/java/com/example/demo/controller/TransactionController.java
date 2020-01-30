package com.example.demo.controller;

import com.example.demo.dto.CardDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //OVO SE POGADJA IZ BANKE PRODAVCA
    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> postPaymentRequest(@RequestBody RequestDto requestDto, @PathVariable String url) {
        ResponseDto responseDto = transactionService.persistTransaction(url, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
