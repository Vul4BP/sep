package com.example.bankservice.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", unique = false, nullable = true)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(String magazineId) {
        this.magazineId = magazineId;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Column(name = "url", unique = false, nullable = false)
    private String url;

    @Column(name = "magazineId", unique = false, nullable = false)
    private String magazineId;

    public Payment() {
    }

    @Column(name = "paid", unique = false, nullable = false)
    private Boolean paid;
}
