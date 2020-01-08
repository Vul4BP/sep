package com.example.bankservice.Dto;

import java.sql.Date;
import lombok.Data;

@Data
public class CardDto {
    private String pan;

    private Integer securityCode;

    private String holderName;

    private Date validTo;
}
