package com.darcikhey.weather.entity;

public class City {

    private String name;
    private String cityId;

    public City(String name, String cityId) {
        this.name = name;
        this.cityId = cityId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
