package com.darcikhey.weather.repository;

import com.darcikhey.weather.entity.City;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.json.Json;
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
            String jsonString = "";

            String line = "";
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                jsonString += line;
            }
            InputStream fin = new FileInputStream((jsonString));
            JsonReader jsonReader = Json.createReader(fin);
            JsonObject jsonObject = jsonReader.readObject();
            String name = jsonObject.getString("name");
            System.out.println(name);

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
