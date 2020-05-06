package com.order.sys.bean.dto;

public class MessageOffice {

    private Integer officeId;
    private String officeDesc;
    private String officeAddressDesc;
    private Integer areaId;
    private String areaDesc;
    private Integer cityId;
    private String cityDesc;

    public MessageOffice() {
    }

    public MessageOffice(Integer officeId, String officeDesc, String officeAddressDesc, String areaDesc, String cityDesc) {
        this.officeId = officeId;
        this.officeDesc = officeDesc;
        this.officeAddressDesc = officeAddressDesc;
        this.areaDesc = areaDesc;
        this.cityDesc = cityDesc;
    }

    public MessageOffice(Integer officeId, String officeDesc, String officeAddressDesc, Integer areaId, String areaDesc, Integer cityId, String cityDesc) {
        this.officeId = officeId;
        this.officeDesc = officeDesc;
        this.officeAddressDesc = officeAddressDesc;
        this.areaId = areaId;
        this.areaDesc = areaDesc;
        this.cityId = cityId;
        this.cityDesc = cityDesc;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeDesc() {
        return officeDesc;
    }

    public void setOfficeDesc(String officeDesc) {
        this.officeDesc = officeDesc;
    }

    public String getOfficeAddressDesc() {
        return officeAddressDesc;
    }

    public void setOfficeAddressDesc(String officeAddressDesc) {
        this.officeAddressDesc = officeAddressDesc;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }
}
