package com.example.seller.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="naziv", nullable = false, unique = true)
    private String naziv;

    @Column(name="issn", nullable =  false, unique = true)
    private String issn;

    @Column(name="clanarina", nullable = false)
    private Long clanarina;

    @Column(name="casopis_id")
    private Long casopisId;

    @ManyToMany
    @JoinTable(
            name = "magazine_nacinplacanja",
            joinColumns = @JoinColumn(name = "magazine_id"),
            inverseJoinColumns = @JoinColumn(name = "placanje_id"))
    private List<NacinPlacanja> naciniPlacanja;
}
