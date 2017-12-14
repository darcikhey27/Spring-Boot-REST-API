package com.darcikhey.weather.controller;


import com.darcikhey.weather.entity.City;
import com.darcikhey.weather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.util.Collection;



@Controller
@RequestMapping("/city")
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

    // POST will pass in json value
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCity(@RequestBody JsonObject cityJson) {
        this.cityService.add(cityJson);
    }


}
