package com.order.sys.bean.dto.internal;

public class MessageOfficeBusinessInternal {

    private String businessLevel;
    private String businessName;
    private String businessType;

    public MessageOfficeBusinessInternal() {
    }

    public MessageOfficeBusinessInternal(String businessLevel, String businessName, String businessType) {
        this.businessLevel = businessLevel;
        this.businessName = businessName;
        this.businessType = businessType;
    }

    public String getBusinessLevel() {
        return businessLevel;
    }

    public void setBusinessLevel(String businessLevel) {
        this.businessLevel = businessLevel;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
