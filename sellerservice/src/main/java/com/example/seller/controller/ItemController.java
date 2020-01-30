package com.example.seller.controller;

import com.example.seller.Dto.ItemDto;
import com.example.seller.Dto.RequestDto;
import com.example.seller.models.Item;
import com.example.seller.models.NacinPlacanja;
import com.example.seller.models.Seller;
import com.example.seller.repo.ItemRepository;
import com.example.seller.repo.NacinPlacanjaRepository;
import com.example.seller.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private NacinPlacanjaRepository nacinPlacanjaRepository;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ItemDto> addSeller(@RequestBody ItemDto dto) {
        ItemDto res = itemService.addItem(dto);
        if(res == null){
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ItemDto>(res,HttpStatus.OK);
    }

    @RequestMapping(path = "/get/{genid}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Item> getItem(@PathVariable String genid) {
        Item res = itemRepository.findByGeneratedId(genid);
        if(res == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(path = "/getpayments/{genid}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<NacinPlacanja>> getNacinePlacanja(@PathVariable String genid) {
        Item item = itemRepository.findByGeneratedId(genid);
        Seller res = item.getSeller();
        if(res == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res.getNaciniPlacanja(), HttpStatus.OK);
    }

    @RequestMapping(path = "/paymentmethod", method = RequestMethod.POST)
    public ResponseEntity<?> checkPaymentMethod(@RequestBody RequestDto requestDto){
        Item item = itemRepository.findByGeneratedId(requestDto.getId());
        Seller seller = item.getSeller();
        NacinPlacanja np = nacinPlacanjaRepository.findByName(requestDto.getPayment());
        if(seller == null || np == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String redirectUrl = np.getUrl() + "/payment/" + requestDto.getId();
        /*
        if(seller == null){
            redirectUrl = "https://localhost:5000";
        }else if(requestDto.getPayment().toLowerCase().equals("bitcoin")) {
            redirectUrl = "https://localhost:5001/payment/" + requestDto.getId();
        }else if(requestDto.getPayment().toLowerCase().equals("bank")) {
            redirectUrl = "https://localhost:5002/payment/" + requestDto.getId();
        }else if(requestDto.getPayment().toLowerCase().equals("paypal")) {
            redirectUrl = "https://localhost:5003/payment/" + requestDto.getId();
        }*/
        //return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();

        String body = "{ \"redirectUrl\" : \"" + redirectUrl + "\" }";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
