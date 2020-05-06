package com.order.sys.bean.dto;

import com.order.sys.bean.dto.internal.MessageBusinessRequestInternal;


import java.util.Date;
import java.util.List;

public class MessageEverydayData {

    private Date time;
    private Integer person;
    private List<MessageBusinessRequestInternal> businessList;

    public MessageEverydayData() {
    }

    public MessageEverydayData(Date time, Integer person, List<MessageBusinessRequestInternal> businessList) {
        this.time = time;
        this.person = person;
        this.businessList = businessList;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public List<MessageBusinessRequestInternal> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<MessageBusinessRequestInternal> businessList) {
        this.businessList = businessList;
    }
}
