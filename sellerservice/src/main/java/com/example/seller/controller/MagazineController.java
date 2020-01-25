package com.example.seller.controller;

import com.example.seller.Dto.MagazineDto;
import com.example.seller.Dto.RequestDto;
import com.example.seller.models.Magazine;
import com.example.seller.models.NacinPlacanja;
import com.example.seller.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/magazine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MagazineController {

    @Autowired
    MagazineService magazineService;

    @Value("${app.id}")
    String  instance;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<MagazineDto> addMagazine(@RequestBody MagazineDto dto) {
        MagazineDto magRes = magazineService.addCasopis(dto);
        if(magRes==null){
            return new ResponseEntity<>(magRes, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<MagazineDto>(magRes,HttpStatus.OK);
    }

    @RequestMapping(path = "/getpayments/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<NacinPlacanja>> getNacinePlacanja(@PathVariable Long id) {
        Magazine magRes = magazineService.getByMagazineId(id);
        if(magRes==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(magRes.getNaciniPlacanja(), HttpStatus.OK);
    }

    @RequestMapping(path = "/paymentmethod", method = RequestMethod.POST)
    public ResponseEntity<?> checkPaymentMethod(@RequestBody RequestDto requestDto){
        Long id = Long.parseLong(requestDto.getId());
        Magazine mag = magazineService.getByMagazineId(id);
        String redirectUrl = "";
        if(mag == null){
            redirectUrl = "https://localhost:5000";
        }else if(requestDto.getPayment().equals("bitcoin")) {
            redirectUrl = "https://localhost:5001/payment/" + requestDto.getId();
        }else if(requestDto.getPayment().equals("bank")) {
            redirectUrl = "https://localhost:5002/payment/" + requestDto.getId();
        }else if(requestDto.getPayment().equals("Paypal")) {
            redirectUrl = "https://localhost:5003/payment/" + requestDto.getId();
        }

        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();

        String body = "{ \"redirectUrl\" : \"" + redirectUrl + "\" }";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Magazine> getMagazine(@PathVariable Long id) {
        Magazine magRes = magazineService.getByMagazineId(id);
        if(magRes==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(magRes, HttpStatus.OK);
    }

    //TEST METHOD
    @RequestMapping(value="getjson",method = RequestMethod.GET)
    public  String getJSON()
    {
        return "JSON from sellers service. Hello from instance = " + instance;
    }

}

