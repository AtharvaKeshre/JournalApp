package com.atharva.journalapp.service;

import com.atharva.journalapp.api.response.WeatherResponse;
import com.atharva.journalapp.cache.AppCache;
import com.atharva.journalapp.constants.PlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.CITY,city).replace(PlaceHolder.API_KEY,apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;

    }
}
