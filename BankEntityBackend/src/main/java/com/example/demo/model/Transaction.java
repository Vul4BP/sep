package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    private String status;

    @Column(unique = true)
    private String orderId;

    private String timestamp;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne()
    private Account payer;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne()
    private Account merchant;
}
