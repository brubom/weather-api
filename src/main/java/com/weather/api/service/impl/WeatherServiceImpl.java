package com.weather.api.service.impl;

import com.weather.api.gateway.WeatherGateway;
import com.weather.api.gateway.dto.Weather;
import com.weather.api.service.WeatherService;
import com.weather.api.service.dto.WeatherAverageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherServiceImpl implements WeatherService {

    private final Double KelvinConstant = 273.15;

    @Autowired
    private WeatherGateway weatherGateway;

    /**
     * This service returns the weather
     *
     * @param city - City for weather forecast
     * @return a single average for next 3 days
     */
    @Override
    public WeatherAverageDetails getWeather(String city) throws IOException {

        List<Weather> weatherList = weatherGateway.getWeather(city);

        return convertWatherToDetails(weatherList);

    }

    /**
     * convert the output of external api into weather average
     * @param weathers - List of weather for the next 5 days
     * @return weather average
     */
    private WeatherAverageDetails convertWatherToDetails(List<Weather> weathers){

        if (weathers == null || weathers.size() == 0)
            return null;

        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusDays(4);

        List<Weather> filtered = weathers.stream().filter(f ->
                f.getForecastDate().isAfter(currentDate) && f.getForecastDate().isBefore(endDate))
            .collect(Collectors.toList());


        List<Weather> daytimeFiltered = filtered.stream()
                .filter(f -> f.isDay())
                .collect(Collectors.toList());

        List<Weather> nighttimeFiltered = filtered.stream()
                .filter(f -> !f.isDay())
                .collect(Collectors.toList());

        Double dayTimeAverage = daytimeFiltered.stream()
                .collect(Collectors.averagingDouble( v-> v.getAvgTemp())) - KelvinConstant;

        Double nightTimeAverage = nighttimeFiltered.stream()
                .collect(Collectors.averagingDouble( v-> v.getAvgTemp())) - KelvinConstant;

        Double pressureAverage = filtered.stream()
                .collect(Collectors.averagingDouble( v-> v.getPressure()));

        WeatherAverageDetails weatherAverageDetails = new WeatherAverageDetails();
        weatherAverageDetails.setNightTimeAverageTemperature(new BigDecimal(nightTimeAverage));
        weatherAverageDetails.setDayTimeAverageTemperature(new BigDecimal(dayTimeAverage));
        weatherAverageDetails.setAveragePressure(new BigDecimal(pressureAverage));
        weatherAverageDetails.setCity(weathers.get(0).getCity());

        return weatherAverageDetails;

    }


}
