package com.cgs.example.weathereurekaclient.controller;

import com.cgs.example.weathereurekaclient.service.CityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityClient cityClient;

    @GetMapping("/cities")
    public String cityList(){

        return cityClient.listCity();
    }
}
