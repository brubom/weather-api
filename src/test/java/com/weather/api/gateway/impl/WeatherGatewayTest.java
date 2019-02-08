package com.weather.api.gateway.impl;


import com.weather.api.gateway.dto.Weather;
import com.weather.api.util.CurrentDateMock;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
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
@TestPropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherGatewayTest {


    @InjectMocks
    private WeatherGatewayImpl weatherGateway;

    @Mock
    private RestTemplate restTemplate;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_weather() throws IOException {

        String city = "Sao Paulo";
        Weather weather = new Weather();
        weather.setCity(city);
        weather.setAvgTemp(300.00f);
        weather.setForecastDateTime(CurrentDateMock.getMockCurrentDate());
        weather.setMaxTemp(303.00f);
        weather.setMinTemp(299.00f);
        weather.setPressure(100);

        String jsonResponse = IOUtils.toString(
                this.getClass().getResourceAsStream("/SaoPaulo.json"),
                "UTF-8"
        );

        ResponseEntity responseEntity = new ResponseEntity(jsonResponse, HttpStatus.OK);

        when(restTemplate.getForEntity(
                Mockito.anyString(),
                ArgumentMatchers.any(Class.class)))
                .thenReturn(responseEntity);

        List<Weather> weatherList = weatherGateway.getWeather("Sao Paulo");

        assertNotNull(weatherList);
        assertTrue(weatherList.size() > 0);
        assertEquals(weatherList.get(0).getCity(), city);


    }


}
