package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.security.KeyStore;

@SpringBootApplication
public class NcbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NcbackendApplication.class, args);
    }

}
