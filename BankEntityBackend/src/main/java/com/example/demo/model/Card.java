package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pan;

    private Integer securityCode;

    private String holderName;

    private Date validTo;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne()
    private Account account;
}
