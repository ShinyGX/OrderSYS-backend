package com.order.sys.bean.dto;

import com.order.sys.bean.model.ComRule;

import java.util.List;

public class MessageRule {


    private Integer officeId;
    private String officeName;
    private List<ComRule> officeRule;

    public MessageRule() {
    }

    public MessageRule(Integer officeId, String officeName) {
        this.officeId = officeId;
        this.officeName = officeName;
    }

    public MessageRule(String officeName, List<ComRule> officeRule) {
        this.officeName = officeName;
        this.officeRule = officeRule;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public List<ComRule> getOfficeRule() {
        return officeRule;
    }

    public void setOfficeRule(List<ComRule> officeRule) {
        this.officeRule = officeRule;
    }
}
