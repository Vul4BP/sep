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
        m1.setId("ff8081816f3a3693016f3a3f3a950000");

        Magazine m2 = new Magazine();
        m2.setName("Magazine2");
        m2.setPrice(8000);
        m2.setId("ff8081816f3a3693016f3a3f82260001");

        Magazine m3 = new Magazine();
        m3.setName("Magazine3");
        m3.setPrice(100000);
        m3.setId("ff8081816f3a3693016f3a3fa81b0002");

        magazines.add(m1);
        magazines.add(m2);
        magazines.add(m3);

        return magazines;
    }

    @PostMapping("postmagazine")
    public String postMagazine(@RequestBody Magazine magazine)
    {
        return "{ \"redirectUrl\" : \"https://localhost:5000/magazine?id=" + magazine.getId() + "\" }";

    }
}
