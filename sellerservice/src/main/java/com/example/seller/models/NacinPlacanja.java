package com.example.seller.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NacinPlacanja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false, unique = true)
    String name;
}
