package com.cgs.example.weathereurekaclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("WEATHER-CITY")//对应服务名称
public interface CityClient {

    @GetMapping("/cities/city")
    String listCity();
}
