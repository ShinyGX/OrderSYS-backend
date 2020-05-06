package com.order.sys.bean.dto;

import java.util.List;

public class MessageWindow {
    private Integer windowId;
    private String windowName;
    private Integer officeId;
    private String officeName;
    private String officeAddress;
    private Integer businessTypeId;
    private String businessDesc;
    private List<String> businessName;

    public MessageWindow() {
    }

    public MessageWindow(Integer windowId, String windowName, Integer officeId, String officeName, String officeAddress, Integer businessTypeId, String businessDesc) {
        this.windowId = windowId;
        this.windowName = windowName;
        this.officeId = officeId;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
        this.businessTypeId = businessTypeId;
        this.businessDesc = businessDesc;
    }

    public MessageWindow(Integer windowId, String windowName, Integer officeId, String officeName, String officeAddress, Integer businessTypeId, String businessDesc, List<String> businessName) {
        this.windowId = windowId;
        this.windowName = windowName;
        this.officeId = officeId;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
        this.businessTypeId = businessTypeId;
        this.businessDesc = businessDesc;
        this.businessName = businessName;
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

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public List<String> getBusinessName() {
        return businessName;
    }

    public void setBusinessName(List<String> businessName) {
        this.businessName = businessName;
    }
}
