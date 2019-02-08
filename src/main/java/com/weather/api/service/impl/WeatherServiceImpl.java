package com.weather.api.service.impl;

import com.weather.api.service.WeatherService;
import com.weather.api.service.dto.WeatherAverageDetails;
import org.springframework.beans.factory.annotation.Value;

public class WeatherServiceImpl implements WeatherService {

    @Value("${weatherapi.openweather.endpoint}")
    private String openWeatherEndpoint;

    @Value("${weatherapi.openweather.apikey}")
    private String openWeatherApiKey;
    /**
     * This service returns the weather
     *
     * @param city
     * @return
     */
    @Override
    public WeatherAverageDetails getWeather(String city) {
       return null;
    }
}
