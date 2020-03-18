package com.cgs.example.weatherreport.client;

import com.cgs.example.weatherreport.vo.City;
import com.cgs.example.weatherreport.vo.WeatherReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("WEATHER-ZUUL")
public interface DataClient {

    /**
     * 根据城市ID查询天气数据
     * @param cityId
     * @return
     */
    @GetMapping("/data/cityId")
    WeatherReponse getDataByCityId(@RequestParam("cityId") String cityId);

    /**
     * 获取城市列表
     * @return
     * @throws Exception
     */
    @GetMapping("/city/cities/city")
    List<City> listCity() throws Exception;
}
