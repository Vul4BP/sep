package com.example.paypal.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String planId;

    private String agreementToken;

    @ManyToOne
    private Seller seller;

    private String amount;

    private String frequency;

    private String frequencyInterval;

    private String cycles;

    private String status;
}
