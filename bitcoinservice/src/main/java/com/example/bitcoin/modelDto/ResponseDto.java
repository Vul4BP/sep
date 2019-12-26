package com.example.bitcoin.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDto {
    private Integer id;

    private String status;

    @JsonProperty(value = "price_amount")
    private String priceAmount;

    public ResponseDto(Integer id, String status, String priceAmount, String paymentUrl) {
        this.id = id;
        this.status = status;
        this.priceAmount = priceAmount;
        this.paymentUrl = paymentUrl;
    }

    public Integer getId() {
        return id;
    }

    public ResponseDto() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(String priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    @JsonProperty(value = "payment_url")
    private String paymentUrl;
}
