package com.order.sys.bean.dto.internal;

public class MessageOfficeInternal {

    private Integer staffId;
    private String staffName;
    private String staffSex;

    public MessageOfficeInternal() {
    }

    public MessageOfficeInternal(Integer staffId, String staffName, String staffSex) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffSex = staffSex;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }
}
