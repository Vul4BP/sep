package com.example.bankservice.config;

public class VarConfig {

    public static String paymentStatusCreated = "Created";
    public static String paymentStatusSuccess = "Success";
    public static String paymentStatusFailed = "Failed";
    public static String paymentStatusError = "Error";

    //------------------ONE COMPUTER------------------
    /*
    public static String paymentSuccessUrl = "https://localhost:8443/bankservice/success/";
    public static String paymentFailedUrl = "https://localhost:8443/bankservice/failed/";
    public static String paymentErrorUrl = "https://localhost:8443/bankservice/error/";

    public static String bankPaymentUrl = "http://localhost:8091/payment";
    public static String bankFrontend = "http://localhost:5010/";

    public static String paymentRedirectUrl = "https://localhost:5004";
    */

    //------------------MORE COMPUTERS------------------
    //ip adresa gde se nalazi kp
    public static String paymentSuccessUrl = "https://192.168.1.109:8443/bankservice/success/";
    public static String paymentFailedUrl = "https://192.168.1.109:8443/bankservice/failed/";
    public static String paymentErrorUrl = "https://192.168.1.109:8443/bankservice/error/";

    //ip adresa gde se nalazi banka
    public static String bankPaymentUrl = "http://192.168.73.241:8091/payment";
    public static String bankFrontend = "http://192.168.73.241:5010/";

    //ip adresa gde se nalazi naucna centrala
    public static String paymentRedirectUrl = "https://192.168.1.170:5004";

}
