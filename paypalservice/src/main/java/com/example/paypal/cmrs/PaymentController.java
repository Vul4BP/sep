package com.example.paypal.cmrs;

import com.example.paypal.dto.CreatePaymentRequest;
import com.example.paypal.dto.CreatePaymentResponse;
import com.example.paypal.dto.ExecutePaymentRequest;
import org.hibernate.query.QueryParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/api/paypal")
@RestController
@CrossOrigin("*")
public class PaymentController {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    PaymentService paymentService;

    @PostMapping
    public CreatePaymentResponse create(@RequestBody CreatePaymentRequest request) {
        System.out.println(request);
        return paymentService.createPayment(request);
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody ExecutePaymentRequest request) {
        System.out.println(request.getPayerID());
        return new ResponseEntity<String>(paymentService.executePayment(request), HttpStatus.OK);
    }

    /*
    @GetMapping(value = "/cancel/{id}")
    public void getFailedPayment(@PathVariable(value = "id") Long id, @RequestParam(value = "token", required = false) String token) {
        LOGGER.error("Payment canceled...");
        //response.sendRedirect("https://localhost:5004");
    }

    @GetMapping("/success/{id}")
    public void getSuccessPayment(@PathVariable Long id, HttpServletResponse response) throws IOException {
        LOGGER.error("Payment success...");
        response.sendRedirect("https://localhost:5004");
    }*/

    /*
    @GetMapping("/getjson")
    public String getJson()  {
        //String redirectUrl = paymentService.cancelPayment(id);
        //LOGGER.error("Payment canceled...");
        return "{ \"test\" : \"test\" }";
    }*/

    @GetMapping("/cancel/{id}")
    public String cancelPayment(@PathVariable("id") Long id)  {
        //String redirectUrl = paymentService.cancelPayment(id);
        LOGGER.error(id.toString());
        LOGGER.error("Payment canceled...");
        return "CANCELED";
    }
}
