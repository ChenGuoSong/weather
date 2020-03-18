package com.cgs.example.weatherreport.controller;

import com.cgs.example.weatherreport.client.DataClient;
import com.cgs.example.weatherreport.service.WeatherReportService;
import com.cgs.example.weatherreport.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/report")
public class WeatherReportController {

    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService weatherReportService;

    @Autowired
    private DataClient dataClient;

    @RequestMapping("/cityId")
    @ExceptionHandler
    public ModelAndView getReportByCityId(@RequestParam("cityId") String cityId, Model model) throws Exception {

        List<City> cityList = null;

        try{
            //由城市数据API微服务提供数据
            cityList = dataClient.listCity();
        }catch (Exception e){

            LOGGER.error("error:",e);
        }

        model.addAttribute("title", "天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("report", weatherReportService.getReportByCityId(cityId));

        return new ModelAndView("report", "reportModel", model);
    }
}
