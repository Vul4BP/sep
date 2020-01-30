package com.example.seller.controller;

import com.example.seller.Dto.RequestDto;
import com.example.seller.Dto.SellerDto;
import com.example.seller.models.NacinPlacanja;
import com.example.seller.models.Seller;
import com.example.seller.repo.NacinPlacanjaRepository;
import com.example.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {

    @Autowired
    SellerService sellerService;

    @Autowired
    NacinPlacanjaRepository nacinPlacanjaRepository;

    @Value("${app.id}")
    String  instance;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<SellerDto> addSeller(@RequestBody SellerDto dto) {
        SellerDto seller = sellerService.getByEmail(dto.getEmail());
        if(seller != null){
            return new ResponseEntity<SellerDto>(seller,HttpStatus.OK);
        }
        
        SellerDto res = sellerService.addSeller(dto);
        if(res == null){
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SellerDto>(res,HttpStatus.OK);
    }

    @RequestMapping(path = "/get/{genid}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Seller> getMagazine(@PathVariable String genid) {
        Seller res = sellerService.getByGeneratedId(genid);
        if(res == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //TEST METHOD
    @RequestMapping(value="getjson",method = RequestMethod.GET)
    public  String getJSON()
    {
        return "JSON from sellers service. Hello from instance = " + instance;
    }

}
