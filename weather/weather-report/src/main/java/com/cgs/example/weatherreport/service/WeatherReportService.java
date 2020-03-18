package com.cgs.example.weatherreport.service;


import com.cgs.example.weatherreport.vo.Weather;

public interface WeatherReportService {

    Weather getReportByCityId(String cityId);
}
