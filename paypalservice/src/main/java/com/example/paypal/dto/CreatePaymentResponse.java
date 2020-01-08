package com.example.paypal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {

    private String approvalUrl;

    private String id;
}
