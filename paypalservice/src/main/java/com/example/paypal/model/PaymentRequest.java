package com.example.paypal.model;

import com.example.paypal.model.enumeration.PaymentIntent;
import com.example.paypal.model.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class PaymentRequest {

    private Double price;
    private String currency;
    private String nameOfJournal;
    private PaymentIntent paymentIntent;
    private PaymentMethod paymentMethod;
    private String description;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNameOfJournal() {
        return nameOfJournal;
    }

    public void setNameOfJournal(String nameOfJournal) {
        this.nameOfJournal = nameOfJournal;
    }

    public PaymentIntent getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(PaymentIntent paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
