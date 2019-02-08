package com.weather.api.controller;

import com.weather.api.service.WeatherService;
import com.weather.api.service.dto.WeatherAverageDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;


    @Test
    public void should_get_weather_average_details() throws Exception {

        WeatherAverageDetails weatherAverageDetails = new WeatherAverageDetails();
        weatherAverageDetails.setCity("Sao Paulo");
        weatherAverageDetails.setAveragePressure(100f);
        weatherAverageDetails.setDayTimeAverageTemperature(200f);
        weatherAverageDetails.setNightTimeAverageTemperature(200f);

        ResultActions actions =
                this.mockMvc.perform(
                        get("/data?city=Sao Paulo")
                )
                .andDo(print());

        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.data.city").value("Sao Paulo"));

    }

}
