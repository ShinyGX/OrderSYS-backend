package com.order.sys.bean;

import java.util.Date;

public class EventNotify {

    private int id;
    private Date triggerTime;
    private String business;
    private int no;
    private String address;

    public EventNotify() {
    }

    public EventNotify(int id, Date triggerTime, String business, int no, String address) {
        this.id = id;
        this.triggerTime = triggerTime;
        this.business = business;
        this.no = no;
        this.address = address;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EventNotify{" +
                "business='" + business + '\'' +
                ", no=" + no +
                ", address='" + address + '\'' +
                '}';
    }
}
