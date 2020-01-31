package com.example.demo.payment;

import com.example.demo.account.AccountService;
import com.example.demo.account.AccountServiceImpl;
import com.example.demo.model.Payment;
import com.example.demo.card.CardDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final AccountService accountService;

    public PaymentController(PaymentServiceImpl paymentService, AccountServiceImpl accountService) {
        this.paymentService = paymentService;
        this.accountService = accountService;
    }

    //OVO SE POGADJA IZ MIKROSERVISA BANKE DA SE DOBIJE URL
    @PostMapping
    public ResponseEntity<?> postPaymentRequest(@RequestBody ExternalBankPaymentRequest kpRequestDto) {
        ExternalBankPaymentResponse response = paymentService.handleKpRequest(kpRequestDto);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ExternalBankPaymentResponse>(response, HttpStatus.OK);
    }

    //POST CARD DATA AND URL
    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    public ResponseEntity<?> postCardData(@RequestBody CardDataDto cardDataDto, @PathVariable String url) {
        Payment payment = paymentService.findByUrl(url);
        if(payment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> lista = paymentService.submitCardData(cardDataDto, url);
        String body = "{ \"url\" : \"" + lista.iterator().next() + "\", \"id\" : \"" + url + "\" }";
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    //OVO SE POGADJA ZA PROVERU STATUSA IZ TASKA
    @GetMapping("/get/{url}")
    public ResponseEntity<?> getByUrl(@PathVariable String url){
        Payment payment = paymentService.findByUrl(url);
        if(payment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(payment.getStatus(), HttpStatus.OK);
    }

    @GetMapping("/{url}")
    public Payment getPaymentPage(@PathVariable String url) {
        return paymentService.findByUrl(url);
    }
}