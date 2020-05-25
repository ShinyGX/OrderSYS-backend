package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "com_business")
public class ComBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Integer business_id;

    @Column(name = "business_desc")
    private String business_desc;

    @Column(name = "business_detail")
    private String business_detail;

    @Column(name = "business_type_id")
    private Integer business_type_id;

    @Column(name = "is_delete")
    private Integer is_delete;

    public ComBusiness(Integer business_id, String business_desc, String business_detail) {
        this.business_id = business_id;
        this.business_desc = business_desc;
        this.business_detail = business_detail;
    }

    public ComBusiness(Integer business_id, String business_desc, String business_detail, Integer business_type_id) {
        this.business_id = business_id;
        this.business_desc = business_desc;
        this.business_detail = business_detail;
        this.business_type_id = business_type_id;
    }

    public ComBusiness(Integer business_id, String business_desc, String business_detail, Integer business_type_id, Integer is_delete) {
        this.business_id = business_id;
        this.business_desc = business_desc;
        this.business_detail = business_detail;
        this.business_type_id = business_type_id;
        this.is_delete = is_delete;
    }

    public ComBusiness() {
    }

    public ComBusiness(Integer business_id, String business_desc) {
        this.business_id = business_id;
        this.business_desc = business_desc;
    }

    public ComBusiness(String business_desc, String business_detail, Integer business_type_id) {
        this.business_desc = business_desc;
        this.business_detail = business_detail;
        this.business_type_id = business_type_id;
    }

    public ComBusiness(String business_desc, String business_detail) {
        this.business_desc = business_desc;
        this.business_detail = business_detail;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }

    public String getBusiness_detail() {
        return business_detail;
    }

    public void setBusiness_detail(String business_detail) {
        this.business_detail = business_detail;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_desc() {
        return business_desc;
    }

    public void setBusiness_desc(String business_desc) {
        this.business_desc = business_desc;
    }
}
