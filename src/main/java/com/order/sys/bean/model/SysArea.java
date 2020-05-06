package com.order.sys.bean.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sys_area")
public class SysArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Integer area_id;

    @Column(name = "area_desc")
    private String area_desc;

    @Column(name = "city_id")
    @JsonIgnore
    private Integer city_id;

    public SysArea() {
    }

    public SysArea(Integer area_id, String area_desc, Integer city_id) {
        this.area_id = area_id;
        this.area_desc = area_desc;
        this.city_id = city_id;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public String getArea_desc() {
        return area_desc;
    }

    public void setArea_desc(String area_desc) {
        this.area_desc = area_desc;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }
}
