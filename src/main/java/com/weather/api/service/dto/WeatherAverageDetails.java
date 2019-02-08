package com.weather.api.service.dto;

import java.math.BigDecimal;

/**
 * DTO Class to hold and deliver weather details.
 *
 * <p>In case we use this class to also add a weather entry.</p>
 */
public class WeatherAverageDetails {


    private String city;
    private BigDecimal dayTimeAverageTemperature;
    private BigDecimal nightTimeAverageTemperature;
    private BigDecimal averagePressure;

    public WeatherAverageDetails(){

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getDayTimeAverageTemperature() {
        return dayTimeAverageTemperature.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public void setDayTimeAverageTemperature(BigDecimal dayTimeAverageTemperature) {
        this.dayTimeAverageTemperature = dayTimeAverageTemperature.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getNightTimeAverageTemperature() {
        return nightTimeAverageTemperature.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public void setNightTimeAverageTemperature(BigDecimal nightTimeAverageTemperature) {
        this.nightTimeAverageTemperature = nightTimeAverageTemperature.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAveragePressure() {
        return averagePressure.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public void setAveragePressure(BigDecimal averagePressure) {

        this.averagePressure = averagePressure.setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}
