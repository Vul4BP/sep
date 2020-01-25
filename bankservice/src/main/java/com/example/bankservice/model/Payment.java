package com.example.bankservice.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String status;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;

    @ManyToOne
    private Seller seller;
}
