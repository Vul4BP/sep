package com.example.bankservice.controller;

import com.example.bankservice.Dto.SellerDto;
import com.example.bankservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {

    @Autowired
    SellerService sellerService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<SellerDto> addSeller(@RequestBody SellerDto dto) {
        SellerDto sellerRes = sellerService.addSeller(dto);
        if(sellerRes == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SellerDto>(sellerRes,HttpStatus.OK);
    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody boolean getSeller(@PathVariable Long id){
        SellerDto sellerRes = sellerService.findByMagazineId(id);
        if(sellerRes == null){
            return false;
        }
        return true;
    }
}
