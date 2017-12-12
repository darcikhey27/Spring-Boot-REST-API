package com.darcikhey.weather.repository;

import com.darcikhey.weather.entity.City;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

@Repository
public class CityRepository {
    private static String API_KEY = "&units=imperial&APPID=8ca04c5bef3b3f954c46059bf47639bd";
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    //$json  = file_get_contents(Location::$BASE_URL + $city_name + Location::$API_KEY);
    private static HashMap<Integer, City> citiesRepository;

    public CityRepository() {
        citiesRepository = new HashMap<>();
        citiesRepository.put(1234, new City("Spokane", "1234"));
        citiesRepository.put(1212, new City("Seattle", "1212"));
        addSpokaneAndSeattle();
    }

    private void addSpokaneAndSeattle() {

        try {
            URL urlTest = new URL(BASE_URL+"Spokane"+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) urlTest.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
//            JSONParser parser = new JSONParser();
//            JSONObject json = (JSONObject) parser.parse(stringToParse);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
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
    }

    public void addCity(Integer id, City city) {
        citiesRepository.put(id, city);
        System.out.println("Adding city to repository");
    }

    public Collection<City> getAllCities() {
        return citiesRepository.values();
    }


}
