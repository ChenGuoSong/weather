package com.cgs.example.weathercollection.job;

import com.cgs.example.weathercollection.service.CityClient;
import com.cgs.example.weathercollection.service.WeatherDataService;
import com.cgs.example.weathercollection.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 天气数据同步
 */

public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityClient cityClient;

    @Autowired
    private WeatherDataService weatherDataService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        logger.info("天气数据同步,start");
        //获取城市Id列表
        List<City> cityList = null;
        try {
            //城市服务获取城市数据
            cityList = cityClient.listCity();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("error", e);
        }
        if (cityList != null) {
            for (City city : cityList) {
                String cityId = city.getCityId();
                logger.info("cityID:" + cityId);
                weatherDataService.syncDataByCityId(cityId);
            }
        }

        logger.info("天气数据同步,end");
    }
}
