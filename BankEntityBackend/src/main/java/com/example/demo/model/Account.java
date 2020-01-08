package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "account")
    private Client client;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    private List<Card> cards;
}
