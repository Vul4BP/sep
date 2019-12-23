package com.example.seller.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SellerController {
    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Value("${app.id}")
    String  instance;

    @RequestMapping(value="getjson",method = RequestMethod.GET)
    public  String getJSON()
    {
        return "JSON from sellers service. Hello from instance = " + instance;
    }

    @PostMapping("paymentmethod")
    public String helloFromTestApi(@RequestBody String paymentMethod){
        JsonObject jsonObject = new JsonParser().parse(paymentMethod).getAsJsonObject();
        //return "Seller service. Hello from instance = " + instance + ". You picked to use " + jsonObject.get("payment") + " for payment.";
        //String method = jsonObject.get("payment").toString();

        String json = "{ \"result\": " + jsonObject.get("payment").toString() + " }";
        return  json;
    }


}
