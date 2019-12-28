package com.example.bankservice.Dto;

import java.math.BigDecimal;

public class PaymentRequestDto {
    private String magazineId;
    private BigDecimal amount;

    public String getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(String magazineId) {
        this.magazineId = magazineId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentRequestDto() {
    }
}
