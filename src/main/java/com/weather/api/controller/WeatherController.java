package com.weather.api.controller;

import com.weather.api.service.WeatherService;
import com.weather.api.service.dto.WeatherAverageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class WeatherController {


    @Autowired
    private WeatherService weatherService;

    @GetMapping("/data?city={city}")
    public WeatherAverageDetails getWeatherAverage(@RequestParam(value="city") String city){

        return weatherService.getWeather(city);

    }

}
