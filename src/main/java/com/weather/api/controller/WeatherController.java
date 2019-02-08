package com.weather.api.controller;

import com.weather.api.service.WeatherService;
import com.weather.api.service.dto.WeatherAverageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.io.IOException;

/**
 *
 */
@RestController
public class WeatherController {


    @Autowired
    private WeatherService weatherService;

    @GetMapping("/data")
    public WeatherAverageDetails getWeatherAverage(@RequestParam(value="city")
                                                               @Pattern(regexp = "^[a-zA-Z ]*$")
                                                               String city) throws IOException {

        return weatherService.getWeather(city);

    }

}
