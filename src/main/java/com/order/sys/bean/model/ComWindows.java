package com.order.sys.bean.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "com_windows")
public class ComWindows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "window_id")
    private Integer window_id;

    @Column(name = "window_desc")
    private String window_desc;

    @Column(name = "business_type_id")
    @JsonIgnore
    private Integer business_type_id;

    @Column(name = "office_id")
    @JsonIgnore
    private Integer office_id;

    @Column(name = "is_use")
    @JsonIgnore
    private Integer is_use = 1;

    public ComWindows() {
    }



    public ComWindows(String window_desc, Integer business_type_id, Integer office_id) {
        this.window_desc = window_desc;
        this.business_type_id = business_type_id;
        this.office_id = office_id;
    }

    public ComWindows(Integer window_id, String window_desc, Integer business_type_id, Integer office_id) {
        this.window_id = window_id;
        this.window_desc = window_desc;
        this.business_type_id = business_type_id;
        this.office_id = office_id;
    }

    public ComWindows(Integer window_id, String window_desc, Integer business_type_id, Integer office_id, Integer is_use) {
        this.window_id = window_id;
        this.window_desc = window_desc;
        this.business_type_id = business_type_id;
        this.office_id = office_id;
        this.is_use = is_use;
    }

    public Integer getIs_use() {
        return is_use;
    }

    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }

    public String getWindow_desc() {
        return window_desc;
    }

    public void setWindow_desc(String window_desc) {
        this.window_desc = window_desc;
    }

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }

    public Integer getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Integer office_id) {
        this.office_id = office_id;
    }


}
