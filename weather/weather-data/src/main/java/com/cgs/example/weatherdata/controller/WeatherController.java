package com.cgs.example.weatherdata.controller;

import com.cgs.example.weatherdata.service.WeatherDataService;
import com.cgs.example.weatherdata.vo.WeatherReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;

    @RequestMapping("/cityId")
    public WeatherReponse getWeatherByCityId(@RequestParam("cityId") String cityId) {

        return weatherDataService.getDataByCityId(cityId);
    }

    @RequestMapping("/cityName/{cityName}")
    public WeatherReponse getWeatherByCityName(@PathVariable("cityName") String cityName) {

        return weatherDataService.getDataByCityName(cityName);
    }
}
