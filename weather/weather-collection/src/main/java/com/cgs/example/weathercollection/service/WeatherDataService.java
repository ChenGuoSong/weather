package com.cgs.example.weathercollection.service;



public interface WeatherDataService {

    /**
     * 根据cityId同步天气
     *
     * @param cityId
     */
    void syncDataByCityId(String cityId);
}
