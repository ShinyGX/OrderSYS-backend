package com.order.sys.bean.dto;

import java.util.Date;

public class MessageRecode {

    private Integer accountId;
    private String staffName;
    private Date time;
    private String recodeTypeName;
    private Integer recodeTypeId;
    private String recodeDetail;

    public MessageRecode() {
    }

    public MessageRecode(Integer accountId, String staffName, Date time, String recodeTypeName, Integer recodeTypeId, String recodeDetail) {
        this.accountId = accountId;
        this.staffName = staffName;
        this.time = time;
        this.recodeTypeName = recodeTypeName;
        this.recodeTypeId = recodeTypeId;
        this.recodeDetail = recodeDetail;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    public String getRecodeTypeName() {
        return recodeTypeName;
    }

    public void setRecodeTypeName(String recodeTypeName) {
        this.recodeTypeName = recodeTypeName;
    }

    public Integer getRecodeTypeId() {
        return recodeTypeId;
    }

    public void setRecodeTypeId(Integer recodeTypeId) {
        this.recodeTypeId = recodeTypeId;
    }

    public String getRecodeDetail() {
        return recodeDetail;
    }

    public void setRecodeDetail(String recodeDetail) {
        this.recodeDetail = recodeDetail;
    }
}
