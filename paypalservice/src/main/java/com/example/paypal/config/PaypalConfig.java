package com.example.paypal.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class PaypalConfig {

//    //@Value("${paypal.client.app}")
//    private String clientId = "ARHaTb4brmSmFeUAyIXpaTSlXfJ-4RZh8cTsp8VDOkJd0JfoIkgaS3RWCmFQX1lM5LwLAtzbtK7qhJJU";
//    //@Value("${paypal.client.secret}")
//    private String clientSecret = "EPqQ8E0cz7Ic8olK_apeq272JEGj59Xaqe9TJZQwULB0gq4Vst8-VOYU_fTYmCvloXLgd_nv_DrezOX2";
//    //@Value("${paypal.mode}")
//    private String mode = "sandbox";
//
//    @Bean
//    public APIContext apiContext(){return new APIContext(clientId,clientSecret,mode);}

//    @Bean
//    public Map<String, String> paypalSdkConfig(){
//        Map<String, String> sdkConfig = new HashMap<>();
//        sdkConfig.put("mode", mode);
//        return sdkConfig;
//    }
//
//    @Bean
//        public OAuthTokenCredential authTokenCredential(){
//        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
//    }
//
//    @Bean
//    public APIContext apiContext() throws PayPalRESTException{
//        APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
//        apiContext.setConfigurationMap(paypalSdkConfig());
//        return apiContext;
//    }
}


