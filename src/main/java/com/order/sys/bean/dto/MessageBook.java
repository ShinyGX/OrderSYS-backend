package com.order.sys.bean.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBook {


    private Integer businessId;
    private String businessName;
    private String businessDesc;
    private List<Date> usefulTime;

    public MessageBook(Integer businessId, String businessName, String businessDesc) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessDesc = businessDesc;
        usefulTime = new ArrayList<>();
    }

    public MessageBook() {
    }

    public MessageBook(Integer businessId, String businessName, String businessDesc, List<Date> usefulTime) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessDesc = businessDesc;
        this.usefulTime = usefulTime;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public List<Date> getUsefulTime() {
        return usefulTime;
    }

    public void setUsefulTime(List<Date> usefulTime) {
        this.usefulTime = usefulTime;
    }
}
