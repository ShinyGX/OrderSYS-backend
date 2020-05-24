package com.order.sys.bean.dto;


public class ObjCreateOffice {
    private Integer token;
    private String name;
    private String address;
    private Integer areaId;
    private Integer levelId;
    private Integer officeLevel;


    public ObjCreateOffice() {
    }

    public ObjCreateOffice(Integer token, String name, String address, Integer areaId, Integer levelId, Integer officeLevel) {
        this.token = token;
        this.name = name;
        this.address = address;
        this.areaId = areaId;
        this.levelId = levelId;
        this.officeLevel = officeLevel;
    }

    public ObjCreateOffice(Integer token, String name, String address, Integer areaId, Integer levelId) {
        this.token = token;
        this.name = name;
        this.address = address;
        this.areaId = areaId;
        this.levelId = levelId;
    }

    public Integer getOfficeLevel() {
        return officeLevel;
    }

    public void setOfficeLevel(Integer officeLevel) {
        this.officeLevel = officeLevel;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }


}
