package com.example.bankservice.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "account")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account(Client client, @Digits(integer = 10, fraction = 2) BigDecimal amount) {
        this.client = client;
        this.amount = amount;
    }

    @Digits(integer = 10, fraction = 2)
    @Column(name = "amount", unique = false, nullable = false)
    private BigDecimal amount;
}
