package com.example.paypal.controller;

import com.example.paypal.dto.CreatePlanRequestDto;
import com.example.paypal.dto.SubscriptionRequestDto;
import com.example.paypal.service.SubService;
import com.paypal.api.payments.Plan;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping(value = "/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionController {

    @Autowired
    SubService subService;

    @PostMapping(value = "/plan/createAndActivePlan")
    public ResponseEntity<?> createAndActivePlan(@RequestBody CreatePlanRequestDto request) throws PayPalRESTException, IOException {
        Plan plan = subService.create(request);
        String str = "{ \"id\" : \"" + plan.getId() + "\"}";
        return new ResponseEntity(str, HttpStatus.OK);
    }

    @PostMapping(value = "/plan/subscribe")
    public ResponseEntity<?>  subscribeToPlan(@RequestBody SubscriptionRequestDto request) throws PayPalRESTException {
        URL url = subService.subscribeToPlan(request);
        String str = "{ \"url\" : \"" + url.toString() + "\"}";
        return new ResponseEntity(str,HttpStatus.OK);
    }

    @GetMapping(value = "/plan/finishSubscription")
    public ResponseEntity finishSubscription(@RequestParam("token") String token){
        System.out.println("Usao je u finish");
        subService.finishSubscription(token);
        String url = "https://localhost:5004";
        /*String url = "https://localhost:5004";
        String result = "{ \"paymentUrl\" : \"" + url + "\" }";
        return new ResponseEntity<String>(result, HttpStatus.OK);*/
        //return ResponseEntity.ok("Subscription finished");
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url).build();
    }

    @GetMapping(value = "/plan/cancelSubscription")
    public ResponseEntity cancelSubscription(@RequestParam("token") String token){
        subService.cancelSubscription(token);
        String url = "https://localhost:5004";
        /*String url = "https://localhost:5004";
        String result = "{ \"paymentUrl\" : \"" + url + "\" }";
        return new ResponseEntity<String>(result, HttpStatus.OK);*/
        //return ResponseEntity.ok("Subscription finished");
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url).build();
    }
}
