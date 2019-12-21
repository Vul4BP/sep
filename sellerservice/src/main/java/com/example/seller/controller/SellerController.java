package com.example.seller.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class SellerController {
    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Value("${app.id}")
    String  instance;

    //@RequestMapping("/")
    @PostMapping("/")
    public String helloFromTestApi(@RequestBody String paymentMethod){
        return "Seller service. Hello from instance = " + paymentMethod;
    }
}
