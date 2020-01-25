package com.example.paypal.config;

public class VarConfig {
    public static String intent = "authorize";
    public static String mode = "sandbox";
    public static String paymentMethod = "paypal";
    public static String paymentCurrency="USD";
    public static String paymentReturnUrl = "https://localhost:8443/paypalservice/success/";
    public static String paymentCancelUrl = "https://localhost:8443/paypalservice/cancel/";
    public static int paypalApprovalUrlIndex = 1;
    public static String returnUrl = "https://localhost:5004";
}
