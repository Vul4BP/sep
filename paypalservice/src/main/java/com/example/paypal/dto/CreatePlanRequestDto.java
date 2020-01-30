package com.example.paypal.dto;

import com.example.paypal.enumeration.FrequencyPayment;
import com.example.paypal.enumeration.PaymentTypePlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class CreatePlanRequestDto {
    private Double price;
    private String currency;
    private String nameOfJournal;
    private String description;
    private PaymentTypePlan typeOfPlan;
    private FrequencyPayment frequencyPayment;
    private int frequencyInterval;
    private int cycles;
}
