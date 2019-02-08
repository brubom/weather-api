# Weather API

[![Build Status](https://travis-ci.org/brubom/weather-api.svg?branch=master)](https://travis-ci.org/brubom/weather-api)

This is how I solved the challenge. 

# So what?
  - I'm reallying on GateWay class to abstract openweathermap api call
  - Service class manages the transformation and filter, as the openweathermap returns data for five days
  - Spring boot does the magic
  - Swagger for documentation

### How to run
Just do a:
    
    gradew bootrun

And you're good to go.

### About the bonus
  - Input validation to avoid non-text inputs
  - Swagger docs [swaggerpage](http://localhost:8080/swagger-ui.html)
  - Unfortunately, there is no integration test at this moment, I'm tired.
  
