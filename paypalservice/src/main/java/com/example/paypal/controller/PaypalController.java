package com.example.paypal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PaypalController {
    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Value("${app.id}")
    String  instance;

    @RequestMapping("/")
    public String helloFromTestApi(){
        return "Paypal service. Hello from instance = " + instance;
    }

    @RequestMapping(value="getjson",method = RequestMethod.GET)
    public  String getJSON()
    {
        return "JSON from Paypal service. Hello from instance = " + instance;
    }

}


