package com.cgs.example.weatherdata.serviceImpl;

import com.cgs.example.weatherdata.service.WeatherDataService;
import com.cgs.example.weatherdata.vo.WeatherReponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherReponse getDataByCityId(String cityId) {

        String uri = "http://wthrcdn.etouch.cn/weather_mini?citykey=" + cityId;
        return this.doGetWeather(uri);

    }

    @Override
    public WeatherReponse getDataByCityName(String cityName) {

        String uri = "http://wthrcdn.etouch.cn/weather_mini?city=" + cityName;
        return this.doGetWeather(uri);

    }



    private WeatherReponse doGetWeather(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //先查缓存
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("redis has data");
            strBody = ops.get(key);
        } else {
            logger.info("redis don't has data");
            ResponseEntity<String> responseString = restTemplate.getForEntity(uri, String.class);
            if (responseString.getStatusCodeValue() == 200) {//获取状态码
                strBody = responseString.getBody();
            }
            //数据写入
            ops.set(key, strBody, 1800L, TimeUnit.SECONDS);
        }

        ObjectMapper mapper = new ObjectMapper();

        WeatherReponse weatherReponse = null;

        try {
            weatherReponse = mapper.readValue(strBody, WeatherReponse.class);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            logger.error("Error", e);
        }


        return weatherReponse;
    }
}
