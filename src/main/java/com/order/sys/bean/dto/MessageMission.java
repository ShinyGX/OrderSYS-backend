package com.order.sys.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageMission {

    private Integer missionId;
    private Integer businessId;
    private String businessDesc;
    private Integer businessTypeId;
    private String businessTypeDesc;
    @JsonIgnore
    private Integer userId;
    private MessageUser customerInfo;
    private Integer registerId;

    public MessageMission() {
    }

    public MessageMission(Integer missionId, Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc, Integer userId, Integer registerId) {
        this.missionId = missionId;
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
        this.userId = userId;
        this.registerId = registerId;
    }

    public MessageMission(Integer missionId, Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc, Integer userId) {
        this.missionId = missionId;
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
        this.userId = userId;
    }

    public MessageMission(Integer missionId, Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc) {
        this.missionId = missionId;
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
    }

    public MessageMission(Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
    }

    public MessageMission(Integer businessId, String businessDesc, Integer businessTypeId, String businessTypeDesc, MessageUser customerInfo) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
        this.customerInfo = customerInfo;
    }

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
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

    public MessageUser getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(MessageUser customerInfo) {
        this.customerInfo = customerInfo;
    }
}
