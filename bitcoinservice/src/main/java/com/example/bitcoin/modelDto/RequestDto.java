package com.example.bitcoin.modelDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDto {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RequestDto(String email, BigDecimal amount, String redirectUrl) {
        this.email = email;
        this.amount = amount;
        this.redirectUrl = redirectUrl;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RequestDto() {
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private BigDecimal amount;

    private String redirectUrl;
}
