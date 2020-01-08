package com.example.bitcoin.modelDto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RequestDto {
    private String magazineId;

    private BigDecimal amount;
}
