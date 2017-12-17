package com.darcikhey.weather.controller;


import com.darcikhey.weather.entity.City;
import com.darcikhey.weather.service.CityService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.Collection;



@Controller
public class CityController {

    @Autowired
    private CityService cityService;


    // GET
    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getAllCities() {
        System.out.println("in getCities");
        return this.cityService.getAllCities();
    }

    // GET a city by city-name
    @RequestMapping(value = "/get-city/{city-name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCityByName(@PathVariable("city-name") String cityName) {
        System.out.println(cityName);
        return this.cityService.getCityByName(cityName);
    }


    // POST will pass in text value
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/add-city", method = RequestMethod.POST)
    public void addCity(@RequestBody String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        String city_name = jsonObject.getString("city_name");
        System.out.println(city_name);
        this.cityService.add(city_name);
    }


    private JsonObject getJsonObj(String s) {
        JsonReader jsonReader = Json.createReader(new StringReader(s));
        return jsonReader.readObject();
    }

    // site controller

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Index Page");
        return "index";
    }

    @RequestMapping("/post")
    public String post(Model model) {
        model.addAttribute("pageTitle", "POST");
        return "post";
    }
    @RequestMapping("/get")
    public String get(Model model) {
        model.addAttribute("pageTitle", "GET");
        return "get";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("pageTitle", "UPDATE");
        return "update";
    }

    @RequestMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("pageTitle", "DELETE");
        return "delete";
    }



}
