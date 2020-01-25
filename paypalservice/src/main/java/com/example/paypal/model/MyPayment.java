package com.example.paypal.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Data
public class MyPayment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String paymentId;

    private String status;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Seller seller;

}


