package com.example.bankservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", unique = false, nullable = true)
    private BigDecimal amount;

    @Column(name = "url", unique = false, nullable = false)
    private String url;

    @Column(name = "magazineId", unique = false, nullable = false)
    private String magazineId;

    @Column(name = "paid", unique = false, nullable = false)
    private Boolean paid;
}
