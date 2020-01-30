package com.example.bitcoin.modelDto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RequestDto {
    private String email;
    private BigDecimal amount;
}
