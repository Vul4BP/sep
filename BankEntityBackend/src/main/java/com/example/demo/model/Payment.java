package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private BigDecimal amount;

    private String status;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne()
    private Account merchantAccount;
}
