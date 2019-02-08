package com.weather.api.service.impl;

import com.weather.api.gateway.WeatherGateway;
import com.weather.api.gateway.dto.Weather;
import com.weather.api.service.dto.WeatherAverageDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Junit test for WeatherServiceImpl it uses <code>@InjectMocks</code> annotation to inject the
 * mocked dependencies in the class. <code>@Mock</code> annotation is used to create a mocked
 * object. Annotation config is enabled by MockitoAnnotations.initMocks(this).
 *
 * <pre>
 *
 *      @InjectMocks
 *     private WeatherServiceImpl weatherService;
 *
 *     @Mock
 *     private WeatherGateway weatherGateway;
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
public class WeatherServiceTest {

    @InjectMocks
    private WeatherServiceImpl weatherService;

     @Mock
    private WeatherGateway weatherGateway;

     @Before
     public void setUp(){

         MockitoAnnotations.initMocks(this);

     }

     @Test
    public void should_return_weather_average() throws IOException {

         when(weatherGateway.getWeather("Sao Paulo")).thenReturn(getMockWeather());

         WeatherAverageDetails weatherAverageDetails = weatherService.getWeather("Sao Paulo");

         assertNotNull(weatherAverageDetails);
         assertTrue(weatherAverageDetails.getDayTimeAverageTemperature() == 100f);

     }

     private List<Weather> getMockWeather(){

         List<Weather> weatherList = new ArrayList<>();

         String city = "Sao Paulo";
         Weather weather = new Weather();
         weather.setCity(city);
         weather.setAvgTemp(200.03f);
         weather.setForecastDateTime(LocalDateTime.now().plusDays(1));
         weather.setMaxTemp(201.00f);
         weather.setMinTemp(198.00f);
         weather.setPressure(100);
         weatherList.add(weather);

         weather = new Weather();
         weather.setCity(city);
         weather.setAvgTemp(200.03f);
         weather.setForecastDateTime(LocalDateTime.now().plusDays(2));
         weather.setMaxTemp(201.00f);
         weather.setMinTemp(198.00f);
         weather.setPressure(100);
         weatherList.add(weather);

         weather = new Weather();
         weather.setCity(city);
         weather.setAvgTemp(200.03f);
         weather.setForecastDateTime(LocalDateTime.now().plusDays(3));
         weather.setMaxTemp(201.00f);
         weather.setMinTemp(198.00f);
         weather.setPressure(100);
         weatherList.add(weather);

         return weatherList;

     }

}
