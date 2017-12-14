package com.darcikhey.weather.controller;


import com.darcikhey.weather.entity.City;
import com.darcikhey.weather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.util.Collection;



@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    // GET
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Collection<City> getCities() {
        System.out.println("in getCities");
        return this.cityService.getAllCities();
    }

    // POST will pass in text value
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "text/plain")
    public void addCity(@RequestBody String city) {
        System.out.println(city);
        this.cityService.add(city);
    }


}
