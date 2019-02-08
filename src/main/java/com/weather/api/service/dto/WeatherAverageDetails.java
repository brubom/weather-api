package com.weather.api.service.dto;

import java.math.BigDecimal;

/**
 * DTO Class to hold and deliver weather details.
 *
 * <p>In case we use this class to also add a weather entry.</p>
 */
public class WeatherAverageDetails {


    private String city;
    private float dayTimeAverageTemperature;
    private float nightTimeAverageTemperature;
    private float averagePressure;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getDayTimeAverageTemperature() {
        return dayTimeAverageTemperature;
    }

    public void setDayTimeAverageTemperature(float dayTimeAverageTemperature) {
        this.dayTimeAverageTemperature = dayTimeAverageTemperature;
    }

    public float getNightTimeAverageTemperature() {
        return nightTimeAverageTemperature;
    }

    public void setNightTimeAverageTemperature(float nightTimeAverageTemperature) {
        this.nightTimeAverageTemperature = nightTimeAverageTemperature;
    }

    public float getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(float averagePressure) {
        this.averagePressure = averagePressure;
    }
}
