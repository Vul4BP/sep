package com.example.bitcoin.modelDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {
    String apiToken;

    BigDecimal amount;

    String successUrl;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public PaymentDto() {
    }

    public PaymentDto(String apiToken, BigDecimal amount, String successUrl, String cancelUrl, String currency, String title, String redirectUrl, Long paymentId) {
        this.apiToken = apiToken;
        this.amount = amount;
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
        this.currency = currency;
        this.title = title;
        this.redirectUrl = redirectUrl;
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    String cancelUrl;

    String currency;

    String title;

    String redirectUrl;

    Long paymentId;
}
