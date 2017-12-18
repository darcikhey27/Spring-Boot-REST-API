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


    // GET all cities
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
        if(!cityName.matches("^(?i)([a-zA-z\\s+]+)")) {
            return "{status : \"bad string\"}";
        }
        System.out.println(cityName);
        return this.cityService.getCityByName(cityName);
    }


    // POST add one city by name
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/add-city", method = RequestMethod.POST)
    public void addCity(@RequestBody String jsonString) {
        if(!jsonString.matches("^(?i)([a-zA-z\\s+]+)")) {
            return;
        }
        JsonObject jsonObject = getJsonObj(jsonString);
        String city_name = jsonObject.getString("city_name");
        System.out.println(city_name);
        this.cityService.add(city_name);
    }

    // deletes a city by name
    // needs to validate
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete-city/{city_name}", method = RequestMethod.DELETE)
    public void deleteCityByName(@PathVariable("city_name") String cityName) {
        if(!cityName.matches("^(?i)([a-zA-z\\s+]+)")) {
            return;
        }
        System.out.println("delte city: "+ cityName);
        this.cityService.deleteByName(cityName);


    }

    //TODO::.. Implement UPDATE


    //END TODO::..







    /* build a JsonObject from the passed string, string has to be a json string */
    private JsonObject getJsonObj(String s) {
        JsonReader jsonReader = Json.createReader(new StringReader(s));
        return jsonReader.readObject();
    }

    // site controller
    /* maps to the root '/' of the web app or '/index'
    *  returns a html view called index
    *  */

    @RequestMapping(value = {"/index", "/"})
    public String index(Model model) {
        model.addAttribute("pageTitle", "Index Page");
        return "index";
    }

    /* returns a html view file called post.html */
    @RequestMapping("/post")
    public String post(Model model) {
        model.addAttribute("pageTitle", "POST");
        return "post";
    }
    /* returns a html view file called get.html */
    @RequestMapping("/get")
    public String get(Model model) {
        model.addAttribute("pageTitle", "GET");
        return "get";
    }

    /* returns a html view file called update.html */
    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("pageTitle", "UPDATE");
        return "update";
    }

    /* returns a html view file called delete.html */
    @RequestMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("pageTitle", "DELETE");
        return "delete";
    }



}
