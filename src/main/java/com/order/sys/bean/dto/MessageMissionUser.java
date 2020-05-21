package com.order.sys.bean.dto;

import java.util.Date;

public class MessageMissionUser {
    private Integer id;
    private String businessName;
    private String businessType;
    private String officeName;
    private String officeAddress;
    private Date missionTime;
    private Integer isDone;

    public MessageMissionUser() {
    }

    public MessageMissionUser(Integer id, String businessName, String businessType, String officeName, String officeAddress, Date missionTime, Integer isDone) {
        this.id = id;
        this.businessName = businessName;
        this.businessType = businessType;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
        this.missionTime = missionTime;
        this.isDone = isDone;
    }

    public Integer getIsDone() {
        return isDone;
    }

    public void setIsDone(Integer isDone) {
        this.isDone = isDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getMissionTime() {
        return missionTime;
    }

    public void setMissionTime(Date missionTime) {
        this.missionTime = missionTime;
    }
}
