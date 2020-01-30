package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;

public interface ITransactionService {
    ResponseDto persistPayment(RequestDto requestDto);
}
