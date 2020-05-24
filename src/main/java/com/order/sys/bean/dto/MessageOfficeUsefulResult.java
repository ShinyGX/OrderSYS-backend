package com.order.sys.bean.dto;

import java.util.List;
import java.util.Map;

public class MessageOfficeUsefulResult {

    private String cityName;
    private Integer cityId;
    private Map<String,Integer> area;
    private Map<String,Integer> officeLevel;

    public MessageOfficeUsefulResult() {
    }

    public MessageOfficeUsefulResult(String cityName, Integer cityId, Map<String, Integer> area, Map<String, Integer> officeLevel) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.area = area;
        this.officeLevel = officeLevel;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Map<String, Integer> getArea() {
        return area;
    }

    public void setArea(Map<String, Integer> area) {
        this.area = area;
    }

    public Map<String, Integer> getOfficeLevel() {
        return officeLevel;
    }

    public void setOfficeLevel(Map<String, Integer> officeLevel) {
        this.officeLevel = officeLevel;
    }
}
