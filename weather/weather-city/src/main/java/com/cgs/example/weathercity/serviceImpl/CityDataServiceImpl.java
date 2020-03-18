package com.cgs.example.weathercity.serviceImpl;

import com.cgs.example.weathercity.service.CityDataService;
import com.cgs.example.weathercity.util.XmlBuilder;
import com.cgs.example.weathercity.vo.City;
import com.cgs.example.weathercity.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() throws Exception {
        //读取xml
        Resource resource = new ClassPathResource("static/city.xml");
        BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        bf.close();

        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, sb.toString());
        return cityList.getCityList();
    }
}
