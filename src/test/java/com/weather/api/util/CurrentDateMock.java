package com.weather.api.util;

import java.time.*;

/**
 * Helper used to mock current date
 */
public class CurrentDateMock {

    public static LocalDateTime getMockCurrentDate(){

        Clock clock = Clock.fixed(Instant.parse("2019-02-08T10:17:30.00Z"), ZoneId.of("UTC"));
        return LocalDateTime.now(clock);
    }



}