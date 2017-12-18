package com.darcikhey.weather.repository;

import com.darcikhey.weather.entity.City;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class CityRepository {
    private static String API_KEY = "&units=imperial&APPID=8ca04c5bef3b3f954c46059bf47639bd";
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    //$json  = file_get_contents(Location::$BASE_URL + $city_name + Location::$API_KEY);
    private static HashMap<Integer, City> citiesRepository;
    private static HashMap<Integer, JsonObject> citiesJsonRepo;

    public CityRepository() {
        citiesJsonRepo = new HashMap<>();
        citiesRepository = new HashMap<>();
        citiesRepository.put(1234, new City("Test", "1234"));
        citiesRepository.put(1212, new City("Seattle", "1212"));
       createCity("Spokane");
    }
    public City createCity(String cityName) {

        String cityNameJson = "";
        Integer cityId = 0;
        try {
            URL urlTest = new URL(BASE_URL+cityName+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) urlTest.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            String jsonString = "";

            String line = "";
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                jsonString += line;
            }

            JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
            JsonObject jsonObject = jsonReader.readObject();
            cityNameJson = jsonObject.getString("name");
            cityId = jsonObject.getInt("id");

            // add to the json repo
            citiesJsonRepo.put(cityId, jsonObject);
            // add the city to the db
            citiesRepository.put(cityId, new City(cityNameJson, cityId.toString()));

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException--");
            System.out.println(e.getMessage());
        } catch (ProtocolException e) {
            System.out.println("ProtocolException--");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException--");
            System.out.println(e.getMessage());
        }
        return new City(cityNameJson, cityId.toString());
    }

    public void addCity(Integer id, City city) {
        citiesRepository.put(id, city);
        createCity(city.getName());
        System.out.println("Adding city to repository");
    }

    public String getAllCities() {
        System.out.println(citiesJsonRepo.toString());
        return citiesJsonRepo.values().toString();
    }


    public int getCityByName(String cityName) {
        int found = -1;
        for (int i : citiesRepository.keySet()) {
            if(citiesRepository.get(i).getName().equalsIgnoreCase(cityName)) {
                found = i;
            }
        }
        if(found >= 0) {
            return found;
        }
        return -1;
    }

    public String getCityById(int location) {
        return citiesJsonRepo.get(location).toString();
    }

    public void deleteById(int location) {
        citiesJsonRepo.remove(location);
        citiesRepository.remove(location);
    }
}
