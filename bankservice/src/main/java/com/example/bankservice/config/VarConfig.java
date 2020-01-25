package com.example.bankservice.config;

public class VarConfig {
    public static String paymentSuccessUrl = "https://localhost:8443/bankservice/success/";
    public static String paymentCancelUrl = "https://localhost:8443/bankservice/failed/";
    public static String paymentErrorUrl = "https://localhost:8443/bankservice/error/";
    public static String paymentRedirectUrl = "https://localhost:5004";

    public static String bankPaymentUrl = "http://localhost:8091/payment";
    public static String bankFrontend = "http://localhost:5010/";

}
