package com.example.bitcoin.config;

public class VarConfig {
    public static String paymentCurrency = "USD";
    public static String paymentTitle = "Order";
    public static String paymentSuccessUrl = "https://localhost:8443/bitcoinservice/success/";
    public static String paymentCancelUrl = "https://localhost:8443/bitcoinservice/cancel/";
    public static String paymentRedirectUrl = "https://localhost:5004";
    public static String coingateUrl = "https://api-sandbox.coingate.com/v2/orders";
}
