package com.cgs.example.weatherdata.service;

import com.cgs.example.weatherdata.vo.WeatherReponse;

public interface WeatherDataService {

    WeatherReponse getDataByCityId(String cityId);

    WeatherReponse getDataByCityName(String cityName);
}
