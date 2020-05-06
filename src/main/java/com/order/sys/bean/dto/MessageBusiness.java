package com.order.sys.bean.dto;

import com.order.sys.bean.dto.internal.MessageBusinessInternal;


import java.util.List;

public class MessageBusiness {
    private Integer businessId;
    private String businessDesc;
    private String businessTypeDesc;
    private List<MessageBusinessInternal> msg;

    public MessageBusiness() {
    }

    public MessageBusiness(Integer businessId, String businessDesc, List<MessageBusinessInternal> msg) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.msg = msg;
    }

    public MessageBusiness(Integer businessId, String businessDesc, String businessTypeDesc, List<MessageBusinessInternal> msg) {
        this.businessId = businessId;
        this.businessDesc = businessDesc;
        this.businessTypeDesc = businessTypeDesc;
        this.msg = msg;
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

    public List<MessageBusinessInternal> getMsg() {
        return msg;
    }

    public void setMsg(List<MessageBusinessInternal> msg) {
        this.msg = msg;
    }
}
