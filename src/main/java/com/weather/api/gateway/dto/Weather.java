package com.weather.api.gateway.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object to transfer weather data
 */
public class Weather {

    private String city;

    private float maxTemp;
    private float minTemp;
    private float avgTemp;
    private float pressure;
    private LocalDateTime forecastDateTime;
    private LocalDate forecastDate;
    private boolean day = false;

    public LocalDate getForecastDate(){
        return forecastDateTime.toLocalDate();
    }

    public boolean isDay() {
        return (forecastDateTime.getHour() > 6 && forecastDateTime.getHour() < 18);

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(float avgTemp) {
        this.avgTemp = avgTemp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public LocalDateTime getForecastDateTime() {
        return forecastDateTime;
    }

    public void setForecastDateTime(LocalDateTime forecastDateTime) {
        this.forecastDateTime = forecastDateTime;
    }

    @Override
    public String toString(){

        StringBuilder builder = new StringBuilder();
        builder.append("[City:").append(city).append("]");
        builder.append("[maxTemp:").append(maxTemp).append("]");
        builder.append("[minTemp:").append(minTemp).append("]");
        builder.append("[avgTemp:").append(avgTemp).append("]");
        builder.append("[pressure:").append(pressure).append("]");
        builder.append("[forecastDateTime:").append(forecastDateTime).append("]");
        builder.append("[forecastDate:").append(forecastDate).append("]");
        builder.append("[isDay:").append(day).append("]");

        return builder.toString();

    }
}
