package com.example.bankservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "magazine_id", unique = false, nullable = false)
    private Long magazineId;

    @Column(name = "merchant_id", unique = false, nullable = false)
    private String merchantId;

}
