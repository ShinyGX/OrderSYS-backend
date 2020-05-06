package com.order.sys.bean.dto;

public class MessageWorking {
    private Integer workToken;
    private Integer windowsId;
    private String windowDesc;
    private Integer businessTypeId;
    private String businessTypeDesc;

    public MessageWorking() {
    }

    public MessageWorking(Integer workToken, Integer windowsId, String windowDesc, Integer windowTypeId, String windowTypeDesc) {
        this.workToken = workToken;
        this.windowsId = windowsId;
        this.windowDesc = windowDesc;
        this.businessTypeId = windowTypeId;
        this.businessTypeDesc = windowTypeDesc;
    }

    public Integer getWorkToken() {
        return workToken;
    }

    public void setWorkToken(Integer workToken) {
        this.workToken = workToken;
    }

    public Integer getWindowsId() {
        return windowsId;
    }

    public void setWindowsId(Integer windowsId) {
        this.windowsId = windowsId;
    }

    public String getWindowDesc() {
        return windowDesc;
    }

    public void setWindowDesc(String windowDesc) {
        this.windowDesc = windowDesc;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }
}
