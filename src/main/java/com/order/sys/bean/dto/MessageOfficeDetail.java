package com.order.sys.bean.dto;

import com.order.sys.bean.dto.internal.MessageOfficeBusinessInternal;
import com.order.sys.bean.dto.internal.MessageOfficeInternal;

import java.util.List;

public class MessageOfficeDetail {

    private String officeDes;
    private String officeAddress;
    private List<MessageOfficeBusinessInternal> businessList;
    private List<MessageOfficeInternal> staffList;

    public MessageOfficeDetail() {
    }

    public MessageOfficeDetail(String officeDes, String officeAddress, List<MessageOfficeBusinessInternal> businessList, List<MessageOfficeInternal> staffList) {
        this.officeDes = officeDes;
        this.officeAddress = officeAddress;
        this.businessList = businessList;
        this.staffList = staffList;
    }

    public String getOfficeDes() {
        return officeDes;
    }

    public void setOfficeDes(String officeDes) {
        this.officeDes = officeDes;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public List<MessageOfficeBusinessInternal> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<MessageOfficeBusinessInternal> businessList) {
        this.businessList = businessList;
    }

    public List<MessageOfficeInternal> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<MessageOfficeInternal> staffList) {
        this.staffList = staffList;
    }
}
