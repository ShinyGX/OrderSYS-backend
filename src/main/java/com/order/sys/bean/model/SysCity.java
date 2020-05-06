package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "sys_city")
public class SysCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer city_id;

    @Column(name = "city_desc")
    private String city_desc;

    public SysCity(Integer city_id, String city_desc) {
        this.city_id = city_id;
        this.city_desc = city_desc;
    }

    public SysCity() {

    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCity_desc() {
        return city_desc;
    }

    public void setCity_desc(String city_desc) {
        this.city_desc = city_desc;
    }
}
