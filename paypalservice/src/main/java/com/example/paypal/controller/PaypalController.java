package com.example.paypal.controller;

import com.example.paypal.config.PaypalConfig;
import com.example.paypal.model.HelpClass;
import com.example.paypal.model.PaymentRequest1;
import com.example.paypal.model.enumeration.PaymentIntent;
import com.example.paypal.model.enumeration.PaymentMethod;
import com.example.paypal.services.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestController
@RequestMapping (value="/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    private static final String SUCCESS_URL = "/api/pay/success";
    private static final String CANCEL_URL = "/api/pay/cancel";

    @RequestMapping(value="pay",method = RequestMethod.GET)
    public String pay() {
        String cancelUrl = "https://localhost:8443/paypalservice" + CANCEL_URL;
        String successUrl = "https://localhost:8443/paypalservice" + SUCCESS_URL;

        //Payment payment = paypalService.createPayment(request.getPrice(), request.getCurrency(), request.getPaymentMethod(), request.getPaymentIntent(),
        //        request.getDescription(), cancelUrl, successUrl)

        return paypalService.createPayment(200.0, "USD","paypal","authorize","sep",cancelUrl, successUrl);

    }

    //{
        //PaymentRequest1 request = new PaymentRequest1();

        //{"magazineId":"ff8081816f3a0270016f3a1395cd0000","price":9000,"currency":"USD","paymentIntent":"ORDER","paymentMethod":"PAYPAL","description":"OKKK"}

        //request.setMagazineId("ff8081816f3a0270016f3a1395cd0000");
        //request.setPrice(9000.0);
        //request.setCurrency("USD");
        //request.setPaymentIntent("autorize");
        //request.setPaymentMethod("paypal");
        //request.setDescription("OKOK");

        //try {
            //Payment payment = paypalService.createPayment(
            //        request.getPrice(),
            //        request.getCurrency(),
            //        request.getPaymentMethod(),
            //       request.getPaymentIntent(),
            //        request.getDescription(),
            //        cancelUrl,
            //        successUrl);

            //return payment.getLinks().get(1).getHref();
            /*for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    String json = "{ \"result\": \"" + links.getHref() + "\" }";
                    //JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                    //logika da se proveri da li je izabrani nacin placanja omogucen za dati casopis
                    //hc.setUrl(links.getHref());
                    return json;
                }
            }*/

            //return payment.getLinks().get(1).getHref();
        //} catch (PayPalRESTException e) {
            //log.error(e.getMessage());`
        //}

    //}


    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            //System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            //log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(value="getjson",method = RequestMethod.GET)
    public  String getJSON()
    {
        return "JSON from sellers service. Hello from instance " ;
    }

}


