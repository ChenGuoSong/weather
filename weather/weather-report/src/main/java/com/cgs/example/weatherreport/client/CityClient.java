package com.cgs.example.weatherreport.client;

import com.cgs.example.weatherreport.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("WEATHER-CITY")
public interface CityClient {

    @GetMapping("/cities/city")
    List<City> listCity() throws Exception;
}
