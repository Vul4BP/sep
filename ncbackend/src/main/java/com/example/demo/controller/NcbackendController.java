package com.example.demo.controller;

import com.example.demo.Magazine;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@RestController
public class NcbackendController {

    @RequestMapping(value="getdata",method = RequestMethod.GET)
    public List<Magazine> getScietificWork()
    {
        List<Magazine> magazines = new ArrayList();

        Magazine m1 = new Magazine();
        m1.setName("Magazine1");
        m1.setPrice(9000);

        Magazine m2 = new Magazine();
        m2.setName("Magazine2");
        m2.setPrice(8000);

        Magazine m3 = new Magazine();
        m3.setName("Magazine3");
        m3.setPrice(7000);

        magazines.add(m1);
        magazines.add(m2);
        magazines.add(m3);

        return magazines;
    }

    @PostMapping("postmagazine")
    public boolean postMagazine(@RequestBody Magazine magazine){
        return true;
    }
}
