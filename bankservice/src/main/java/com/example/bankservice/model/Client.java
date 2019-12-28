package com.example.bankservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", unique = false, nullable = false)
    private String firstName;

    @Column(name = "lastName", unique = false, nullable = false)
    private String lastName;

    @Column(name = "magazineId", unique = false, nullable = false)
    private String magazineId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", unique = true)
    private Account account;

    @Size(max = 100)
    @Column(name = "merchantPassword", unique = false, nullable = false)
    private String merchantPassword;

    @Size(max = 30)
    @Column(name = "merchantId", unique = false, nullable = false)
    private String merchantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(String magazineId) {
        this.magazineId = magazineId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Client() {
    }

}
