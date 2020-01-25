package com.example.paypal.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_id", unique = false, nullable = false)
    private String clientId;

    @Column(name = "secret", unique = false, nullable = false)
    private String secret;

    @Column(name = "magazine_id", unique = true, nullable = false)
    private Long magazineId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

}

