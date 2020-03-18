package com.cgs.example.weatherdata.vo;


import java.io.Serializable;

/***
 * 相应对象
 *
 */
public class WeatherReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Weather data;

    private Integer status;

    private String desc;

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}