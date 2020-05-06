package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "com_business_type")
public class ComBusinessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_type_id")
    private Integer business_type_id;

    @Column(name = "business_type_desc")
    private String business_type_desc;

    public ComBusinessType() {
    }

    public ComBusinessType(Integer business_type_id, String business_type_desc) {
        this.business_type_id = business_type_id;
        this.business_type_desc = business_type_desc;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }

    public String getBusiness_type_desc() {
        return business_type_desc;
    }

    public void setBusiness_type_desc(String business_type_desc) {
        this.business_type_desc = business_type_desc;
    }
}
