package com.weather.api.gateway.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.gateway.WeatherGateway;
import com.weather.api.gateway.dto.Weather;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherGatewayImpl implements WeatherGateway {

    private static final Logger logger = LogManager.getLogger(WeatherGatewayImpl.class);

    @Value("${weatherapi.openweather.endpoint}")
    private String openWeatherEndpoint;

    @Value("${weatherapi.openweather.apikey}")
    private String openWeatherApiKey;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * A service method to get the weather from an external based on city name
     *
     * @param city - city's name
     * @return Weather details
     */
    @Override
    public List<Weather> getWeather(String city) throws IOException {

        logger.debug("Calling service on endpoint:" + openWeatherEndpoint);
        ResponseEntity<String> response  = null;

        try {
            response = restTemplate.getForEntity(openWeatherEndpoint + "/forecast?q=" +
                    city + "&appid=" + openWeatherApiKey, String.class);
        }catch (Exception ex){
            logger.error("Failed to call openweatherapi on " + openWeatherEndpoint, ex);
            throw  ex;
        }

        List<Weather> weatherList = null;

        try {
            weatherList = fromJsonToWeather(response.getBody());
        }catch (IOException ex){
            logger.error("Failed to transform openweatherapi json. Endpoint " + openWeatherEndpoint, ex);
            throw  ex;
        }

        return weatherList;
    }

    private List<Weather> fromJsonToWeather(String  jsonBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonBody);
        JsonNode list = root.path("list");

        String city = root.path("city").path("name").asText();
        List<Weather> weatherList =  new ArrayList<>();
        for (final JsonNode node : list) {
            Weather weather = new Weather();
            weather.setCity(city);

            JsonNode mainNode = node.path("main");
            weather.setPressure( getFloatFromNode(mainNode, "pressure"));
            weather.setMinTemp(getFloatFromNode(mainNode, "temp_min"));
            weather.setMaxTemp(getFloatFromNode(mainNode, "temp_max"));
            weather.setAvgTemp(( weather.getMaxTemp() + weather.getMinTemp()) / 2 );
            weather.setForecastDateTime(getLocalDateTimeFromNode(node, "dt"));
            weatherList.add(weather);
            logger.debug("Adding WeatherDTO:\n" + weather);
        }

        return weatherList;
    }

    private float getFloatFromNode(JsonNode node, String path){
        String text = node.path(path).asText();

        if(!StringUtils.isEmpty(text)){
            return Float.parseFloat(text);
        }

        return 0f;
    }

    private LocalDateTime getLocalDateTimeFromNode(JsonNode node, String path){

        String text = node.path(path).asText();

        if(!StringUtils.isEmpty(text)){

            return LocalDateTime.ofInstant(Instant.ofEpochSecond( Integer.parseInt(text)),
                            ZoneId.of("UTC"));

        }

        return null;
    }

}
