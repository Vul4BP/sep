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
        return "Seller service. Hello from instance = " + instance + ". You picked to use " + jsonObject.get("payment") + " for payment.";
    }

    @PostMapping("magazinename")
    public List<String> PostMagazineName(@RequestBody String magazinename){

        List<String> retunList = new ArrayList();

        if(magazinename.equals("Magazine1")){
            retunList.add("paypal");
            retunList.add("bitcoin");
            retunList.add("banka");
        }else if(magazinename.equals("Magazine2")){
            retunList.add("paypal");
            retunList.add("bitcoin");
        }else if(magazinename.equals("Magazine3")){
            retunList.add("paypal");
        }else {

        }

        return retunList;

    }
}
