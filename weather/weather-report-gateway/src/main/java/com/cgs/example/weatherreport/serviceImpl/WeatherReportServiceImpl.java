package com.cgs.example.weatherreport.serviceImpl;

import com.cgs.example.weatherreport.client.DataClient;
import com.cgs.example.weatherreport.service.WeatherReportService;
import com.cgs.example.weatherreport.vo.Weather;
import com.cgs.example.weatherreport.vo.WeatherReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private DataClient dataClient;

    @Override
    public Weather getReportByCityId(String cityId) {

        //由天气数据Api微服务来提供
        WeatherReponse reponse = dataClient.getDataByCityId(cityId);

        return reponse.getData();
    }
}
