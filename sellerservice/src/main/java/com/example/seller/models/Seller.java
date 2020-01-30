package com.example.seller.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="generated_id", nullable = false, unique = true)
    private String generatedId;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Item> items;

    @ManyToMany
    @JoinTable(
            name = "seller_nacinplacanja",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "placanje_id"))
    private List<NacinPlacanja> naciniPlacanja;
}
