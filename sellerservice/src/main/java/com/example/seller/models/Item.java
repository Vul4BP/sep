package com.example.seller.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false, unique = false)
    private String name;

    /*
    @Column(name="izdanje", nullable = true, unique = false)
    private Long izdanje;*/

    @Column(name="generated_id", nullable = false, unique = true)
    private String generatedId;

    @Column(name="amount", nullable = false, unique = false)
    private Long amount;

    @ManyToOne()
    private Seller seller;
}
