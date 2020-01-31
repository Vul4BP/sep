package com.example.paypal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class PaypalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaypalApplication.class, args);
    }

}
