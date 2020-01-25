package com.example.bitcoin.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="api_token", nullable = false, unique = false)
    private String apiToken;

    @Column(name="magazine_id", nullable = false, unique = true)
    private Long magazineId;

}

