package com.darcikhey.weather.service;

import com.darcikhey.weather.entity.City;
import com.darcikhey.weather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.util.Collection;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Collection<City> getAllCities() {
        return this.cityRepository.getAllCities();

    }

    public void add(String city) {
        City cityObj = this.cityRepository.createCity(city);
        System.out.println("in Cityservice "+ cityObj.toString());
        this.cityRepository.addCity(Integer.parseInt(cityObj.getCityId()), cityObj);
    }
}
