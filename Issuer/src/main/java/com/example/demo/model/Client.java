package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Size(max = 30)
    private String payerId;

    @Size(max = 100)
    private String payerPassword;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "id", unique = true)
    private Account account;
}
