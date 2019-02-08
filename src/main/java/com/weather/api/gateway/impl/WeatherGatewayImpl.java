package com.weather.api.gateway.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.gateway.WeatherGateway;
import com.weather.api.gateway.dto.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class WeatherGatewayImpl implements WeatherGateway {


    @Value("${weatherapi.openweather.endpoint}")
    private String openWeatherEndpoint;

    @Value("${weatherapi.openweather.apikey}")
    private String openWeatherApiKey;


    /**
     * A service method to get the weather from an external based on city name
     *
     * @param city - city's name
     * @return Weather details
     */
    @Override
    public List<Weather> getWeather(String city) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = URI.create(openWeatherEndpoint + "/forecast?q=" + city + "/appid=" + openWeatherApiKey);

        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class);


        return fromJsonToWeather(response.getBody());
    }

    private List<Weather> fromJsonToWeather(String  jsonBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonBody);
        JsonNode name = root.path("name");

        return null;
    }

}
