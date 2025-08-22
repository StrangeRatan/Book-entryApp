package com.Ratan.my_fist_Demo.Service;

import com.Ratan.my_fist_Demo.ApiResponse.weatherpojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeathService {
    private final  static  String apikey="ec1a176c59adc1c82ab53402a0cda2d4";

    private final  static  String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public weatherpojo getWeather(String city){
        String finalAPI=API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<weatherpojo> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, weatherpojo.class);

        weatherpojo body = response.getBody();
        return body;
    }
}
