package com.order.sys.bean.dto;

import java.sql.Timestamp;
import java.util.Date;

public class MessageStaff {

    private Integer token;
    private Integer staffId;
    private String cityDesc;
    private String areaDesc;
    private String OfficeDesc;
    private String name;
    private String sex;
    private String workAge;
    private Date workAvgTime;
    private Integer levelId;
    private String levelDesc;
    private Integer areaId;
    private Integer cityId;
    private Integer officeId;

    public MessageStaff() {
    }

    public MessageStaff(Integer token, Integer staffId, String cityDesc, String areaDesc, String officeDesc, String name, String sex, String workAge, Date workAvgTime, Integer levelId, String levelDesc, Integer areaId, Integer cityId, Integer officeId) {
        this.token = token;
        this.staffId = staffId;
        this.cityDesc = cityDesc;
        this.areaDesc = areaDesc;
        OfficeDesc = officeDesc;
        this.name = name;
        this.sex = sex;
        this.workAge = workAge;
        this.workAvgTime = workAvgTime;
        this.levelId = levelId;
        this.levelDesc = levelDesc;
        this.areaId = areaId;
        this.cityId = cityId;
        this.officeId = officeId;
    }

    public MessageStaff(Integer staffId, String cityDesc, String areaDesc, String officeDesc, String name, String sex, String workAge, Date workAvgTime, Integer levelId, String levelDesc, Integer areaId, Integer cityId, Integer officeId) {
        this.staffId = staffId;
        this.cityDesc = cityDesc;
        this.areaDesc = areaDesc;
        OfficeDesc = officeDesc;
        this.name = name;
        this.sex = sex;
        this.workAge = workAge;
        this.workAvgTime = workAvgTime;
        this.levelId = levelId;
        this.levelDesc = levelDesc;
        this.areaId = areaId;
        this.cityId = cityId;
        this.officeId = officeId;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public void setWorkAvgTime(Date workAvgTime) {
        this.workAvgTime = workAvgTime;
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

    public MessageStaff(Integer staffId, String cityDesc, String areaDesc, String officeDesc, String name, String sex, String workAge, Date workAvgTime, Integer levelId, String levelDesc) {
        this.staffId = staffId;
        this.cityDesc = cityDesc;
        this.areaDesc = areaDesc;
        OfficeDesc = officeDesc;
        this.name = name;
        this.sex = sex;
        this.workAge = workAge;
        this.workAvgTime = workAvgTime;
        this.levelId = levelId;
        this.levelDesc = levelDesc;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getOfficeDesc() {
        return OfficeDesc;
    }

    public void setOfficeDesc(String officeDesc) {
        OfficeDesc = officeDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public Date getWorkAvgTime() {
        return workAvgTime;
    }

    public void setWorkAvgTime(Timestamp workAvgTime) {
        this.workAvgTime = workAvgTime;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }
}
