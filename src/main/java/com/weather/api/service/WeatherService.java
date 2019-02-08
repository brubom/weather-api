package com.weather.api.service;

import com.weather.api.service.dto.WeatherAverageDetails;

import java.io.IOException;

/**
 * This is a service interface for weather
 */
public interface WeatherService {

    /**
     * This service returns the weather
     * @param city
     * @return
     */
    WeatherAverageDetails getWeather(String city) throws IOException;
}
