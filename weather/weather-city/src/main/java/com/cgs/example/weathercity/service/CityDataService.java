package com.cgs.example.weathercity.service;

import com.cgs.example.weathercity.vo.City;
import java.util.List;

public interface CityDataService {

    List<City> listCity() throws Exception;
}
