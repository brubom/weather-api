package com.weather.api.gateway.impl;


import com.weather.api.gateway.dto.Weather;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Junit test for WeatherGatewayImpl it uses <code>@InjectMocks</code> annotation to inject the
 * mocked dependencies in the class. <code>@Mock</code> annotation is used to create a mocked
 * object. Annotation config is enabled by MockitoAnnotations.initMocks(this).
 *
 * <pre>
 *
 *      @InjectMocks
 *     private WeatherGatewayImpl weatherGateway;
 *
 *     @Mock
 *     private RestTemplate restTemplate;
 *
 *     @Before
 *     public void setUp(){
 *         MockitoAnnotations.initMocks(this);
 *     }
 *
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherGatewayTest {


    @InjectMocks
    private WeatherGatewayImpl weatherGateway;

    @Mock
    private RestTemplate restTemplate;


    @Value("${weatherapi.openweather.endpoint}")
    private String openWeatherEndpoint;

    @Value("${weatherapi.openweather.apikey}")
    private String openWeatherApiKey;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_weather() throws IOException {

        String city = "Sao Paulo";
        Weather weather = new Weather();
        weather.setCity(city);
        weather.setAvgTemp(200.03f);
        weather.setForecastDateTime(LocalDateTime.now());
        weather.setMaxTemp(201.00f);
        weather.setMinTemp(198.00f);
        weather.setPressure(100);

        URI uri = URI.create(openWeatherEndpoint + "/forecast?q=" + city + "/appid=" + openWeatherApiKey);

        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class);

        String jsonResponse = IOUtils.toString(
                this.getClass().getResourceAsStream("SaoPaulo.json"),
                "UTF-8"
        );
        when(restTemplate.getForEntity(uri, String.class))
                .thenReturn(new ResponseEntity(jsonResponse, HttpStatus.OK));

        List<Weather> weatherList = weatherGateway.getWeather("Sao Paulo");

        assertNotNull(weatherList);
        assertTrue(weatherList.size() > 0);
        assertEquals(weatherList.get(0).getCity(), city);


    }


}
