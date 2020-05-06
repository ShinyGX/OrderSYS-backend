package com.order.sys.bean.dto;


public class ObjCreateBusiness {
    private Integer token;
    private String businessName;
    private String businessDetail;
    private Integer businessTypeId;

    public ObjCreateBusiness() {
    }

    public ObjCreateBusiness(Integer token, String businessName, String businessDetail, Integer businessTypeId) {
        this.token = token;
        this.businessName = businessName;
        this.businessDetail = businessDetail;
        this.businessTypeId = businessTypeId;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }



    public String getBusinessDetail() {
        return businessDetail;
    }

    public void setBusinessDetail(String businessDetail) {
        this.businessDetail = businessDetail;
    }
}
