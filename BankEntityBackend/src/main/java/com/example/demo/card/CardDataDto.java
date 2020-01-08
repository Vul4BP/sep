package com.example.demo.card;

import lombok.Data;
import java.sql.Date;

@Data
public class CardDataDto {
    private String pan;

    private Integer securityCode;

    private String holderName;

    private Date validTo;
}
