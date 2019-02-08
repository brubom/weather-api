package com.weather.api.gateway;

import com.weather.api.gateway.dto.Weather;

import java.io.IOException;
import java.util.List;

/**
 * This is an interface showing the gateway(outbound) service for weather
 *
 * @see com.weather.api.gateway.impl.WeatherGatewayImpl
 */
public interface WeatherGateway {


    /**
     * A service method to get the weather from an external based on city name
     * @param city - city's name
     * @return A list of Weather details for next five days
     */
    List<Weather> getWeather(String city) throws IOException;

}
