package com.cgs.example.weathercollection.serviceImpl;

import com.cgs.example.weathercollection.service.WeatherDataService;
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
    public void syncDataByCityId(String cityId) {

        String uri = "http://wthrcdn.etouch.cn/weather_mini?citykey=" + cityId;
        this.saveWeatherData(uri);

    }

    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //通过服务获取数据
        ResponseEntity<String> responseString = restTemplate.getForEntity(uri, String.class);
        if (responseString.getStatusCodeValue() == 200) {//获取状态码
            strBody = responseString.getBody();
        }
        //数据写入
        ops.set(key, strBody, 1800L, TimeUnit.SECONDS);
    }
}
