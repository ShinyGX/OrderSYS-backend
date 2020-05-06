package com.order.sys.bean.dto;


public class ObjCreateWindow {
    private Integer token;
    private String windowName;
    private Integer officeId;
    private Integer businessTypeId;

    public ObjCreateWindow() {
    }

    public ObjCreateWindow(Integer token, String windowName, Integer officeId, Integer businessTypeId) {
        this.token = token;
        this.windowName = windowName;
        this.officeId = officeId;
        this.businessTypeId = businessTypeId;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }
}
