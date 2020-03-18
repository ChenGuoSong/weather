package com.cgs.example.weathercity.controller;

import com.cgs.example.weathercity.service.CityDataService;
import com.cgs.example.weathercity.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityDataService cityDataService;

    @GetMapping("/city")
    public List<City> listCity() throws Exception {

        return cityDataService.listCity();
    }
}


