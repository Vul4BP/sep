package com.example.paypal.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "paypal_email", unique = true, nullable = false)
    private String paypalEmail;

}

