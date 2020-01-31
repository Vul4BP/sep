package com.example.paypal.config;

public class VarConfig {
    public static String paymentStatusCreated = "created";
    public static String paymentStatusSuccess = "Success";
    public static String paymentStatusFailed = "Failed";
    public static String paymentStatusError = "Error";

    public static String clinetId = "AazQvm6OhuRqgWWE32N7VFlNS-lBogXsTFLzp3cPAJalnBjDNNYtjtBCTws0GtXI-R_EvM4enp2UVD88";
    public static String secret = "ELSQrhmpWiNuMQZ12jW0kOP4c7QxyAyJTV_GAIRjwVPaMtqmHVaIPAkf0vkU8Xe6v7Li86g7LL-NbNHV";
    public static String intent = "authorize";
    public static String mode = "sandbox";
    public static String paymentMethod = "paypal";
    public static String paymentCurrency="USD";
    public static String paymentReturnUrl = "https://localhost:8443/paypalservice/success/";
    public static String paymentCancelUrl = "https://localhost:8443/paypalservice/cancel/";
    public static int paypalApprovalUrlIndex = 1;
    public static String returnUrl = "https://localhost:5004";
}
