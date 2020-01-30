package com.example.demo.service;

import com.example.demo.dto.CardDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;

public interface ITransactionService {
    ResponseDto persistTransaction(String url, RequestDto requestDto);
}
