package com.cgs.example.weatherreport.client;

import com.cgs.example.weatherreport.vo.Weather;
import com.cgs.example.weatherreport.vo.WeatherReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("WEATHER-DATA")
public interface WeatherDataClient {

    @GetMapping("/cityId")
    WeatherReponse getDataByCityId(@RequestParam("cityId") String cityId);
}
