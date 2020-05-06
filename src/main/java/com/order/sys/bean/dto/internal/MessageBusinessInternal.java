package com.order.sys.bean.dto.internal;

public class MessageBusinessInternal {
    private Integer officeId;
    private String officeDesc;
    private String officeAddress;
    private String areaAddress;
    private String cityAddress;

    private Integer windowId;
    private String windowDesc;

    public MessageBusinessInternal() {
    }

    public MessageBusinessInternal(Integer officeId, String officeDesc, String officeAddress, String areaAddress, String cityAddress, Integer windowId, String windowDesc) {
        this.officeId = officeId;
        this.officeDesc = officeDesc;
        this.officeAddress = officeAddress;
        this.areaAddress = areaAddress;
        this.cityAddress = cityAddress;
        this.windowId = windowId;
        this.windowDesc = windowDesc;
    }

    public String getWindowDesc() {
        return windowDesc;
    }

    public void setWindowDesc(String windowDesc) {
        this.windowDesc = windowDesc;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeDesc() {
        return officeDesc;
    }

    public void setOfficeDesc(String officeDesc) {
        this.officeDesc = officeDesc;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Integer getWindowId() {
        return windowId;
    }

    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
    }
}
