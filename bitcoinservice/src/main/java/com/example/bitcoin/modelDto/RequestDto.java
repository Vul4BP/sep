package com.example.bitcoin.modelDto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RequestDto {
    private Long magazineId;
    private BigDecimal amount;
}
