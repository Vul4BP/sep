package com.example.bitcoin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinController {
    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Value("${app.id}")
    String  instance;

    @RequestMapping("/")
    public String helloFromTestApi(){
        return "Bitcoin service. Hello from instance = " + instance;
    }

    @RequestMapping(value="getjson",method = RequestMethod.GET)
    public  String getJSON()
    {
        return "{ \"msg\" : \"JSON from Bitcoin service\", \"instance\" : \"" + instance + "\" }";
    }
}
