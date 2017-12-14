package com.darcikhey.weather.controller;


import com.darcikhey.weather.entity.City;
import com.darcikhey.weather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;



@Controller
@RequestMapping("/app")
public class CityController {

    @Autowired
    private CityService cityService;



    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/city")
    @RequestMapping(method = RequestMethod.POST)
    public Collection<City> getCities() {
        System.out.println("in getCities");
        return this.cityService.getAllCities();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCity(@RequestBody City city) {
    }


}
