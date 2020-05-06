package com.order.sys.bean.model;

import javax.persistence.*;

@Table(name = "com_business_level")
@Entity
public class ComBusinessLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_level_id")
    private Integer business_level_id;

    @Column(name = "business_level_desc")
    private String business_level_desc;

    public ComBusinessLevel() {
    }

    public ComBusinessLevel(Integer business_level_id, String business_level_desc) {
        this.business_level_id = business_level_id;
        this.business_level_desc = business_level_desc;
    }

    public Integer getBusiness_level_id() {
        return business_level_id;
    }

    public void setBusiness_level_id(Integer business_level_id) {
        this.business_level_id = business_level_id;
    }

    public String getBusiness_level_desc() {
        return business_level_desc;
    }

    public void setBusiness_level_desc(String business_level_desc) {
        this.business_level_desc = business_level_desc;
    }


}
