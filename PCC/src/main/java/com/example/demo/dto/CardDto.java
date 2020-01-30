package com.example.demo.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class CardDto {
    private String pan;
    private Integer securityCode;
    private String holderName;
    private Date validTo;
}
