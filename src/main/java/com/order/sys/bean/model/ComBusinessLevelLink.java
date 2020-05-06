package com.order.sys.bean.model;



import com.order.sys.constants.BusinessLevelId;

import javax.persistence.*;

@Table(name = "com_business_level_link")
@Entity
@IdClass(BusinessLevelId.class)
public class ComBusinessLevelLink {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Integer business_id;

    @Id
    @Column(name = "business_level_id")
    private Integer business_level_id;

    public ComBusinessLevelLink() {
    }

    public ComBusinessLevelLink(Integer business_id, Integer business_level_id) {
        this.business_id = business_id;
        this.business_level_id = business_level_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getBusiness_level_id() {
        return business_level_id;
    }

    public void setBusiness_level_id(Integer business_level_id) {
        this.business_level_id = business_level_id;
    }
}
