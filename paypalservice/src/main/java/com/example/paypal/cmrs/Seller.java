package com.example.paypal.cmrs;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "magazineID", unique = true, nullable = false)
    private String magazineID;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "merchant_id", unique = true, nullable = false)
    private String merchant_id;

}
