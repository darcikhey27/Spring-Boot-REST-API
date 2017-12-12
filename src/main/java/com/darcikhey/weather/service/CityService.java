package com.darcikhey.weather.service;

import com.darcikhey.weather.entity.City;
import com.darcikhey.weather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Collection<City> getAllCities() {
        return this.cityRepository.getAllCities();

    }
}
