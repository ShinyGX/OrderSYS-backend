package com.order.sys.bean.dto;


public class ObjResetWindow {
    private Integer token;
    private Integer windowId;
    private String windowName;
    private Integer officeId;
    private Integer businessTypeId;


    public ObjResetWindow() {
    }

    public ObjResetWindow(Integer token, Integer windowId, String windowName, Integer officeId, Integer businessTypeId) {
        this.token = token;
        this.windowId = windowId;
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

    public Integer getWindowId() {
        return windowId;
    }

    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
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
