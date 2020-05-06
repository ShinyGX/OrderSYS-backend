package com.order.sys.bean.dto.internal;

public class MessageBusinessRequestInternal {
    private Integer businessId;
    private String businessDesc;
    private Integer businessTypeId;
    private String businessTypeDesc;
    private Integer windowId;
    private String staffName;
    private Integer staffId;
    private String staffSex;
    private Boolean isDone;

    public MessageBusinessRequestInternal() {
    }

    public MessageBusinessRequestInternal(Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc, Integer windowId, String staffName, Integer staffId, String staffSex) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
        this.windowId = windowId;
        this.staffName = staffName;
        this.staffId = staffId;
        this.staffSex = staffSex;
    }

    public MessageBusinessRequestInternal(Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
    }

    public MessageBusinessRequestInternal(Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc, Boolean isDone) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
        this.isDone = isDone;
    }

    public Integer getWindowId() {
        return windowId;
    }

    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
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

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
