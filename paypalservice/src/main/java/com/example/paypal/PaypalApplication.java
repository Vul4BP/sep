package com.example.paypal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@SpringBootApplication
@EnableDiscoveryClient
public class PaypalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaypalApplication.class, args);
    }

}
