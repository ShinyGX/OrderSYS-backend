package com.order.sys.bean.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sys_office")
public class SysOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Integer office_id;

    @Column(name = "office_desc")
    private String office_desc;

    @Column(name = "office_address_desc")
    private String office_address_desc;

    @Column(name = "area_id")
    @JsonIgnore
    private Integer area_id;

    @Column(name = "level_id")
    @JsonIgnore
    private Integer level_id;


    public SysOffice() {
    }

    public SysOffice(String office_desc, String office_address_desc, Integer area_id, Integer level_id) {
        this.office_desc = office_desc;
        this.office_address_desc = office_address_desc;
        this.area_id = area_id;
        this.level_id = level_id;
    }

    public SysOffice(String office_desc, String office_address_desc, Integer area_id) {
        this.office_desc = office_desc;
        this.office_address_desc = office_address_desc;
        this.area_id = area_id;
    }

    public SysOffice(Integer office_id, String office_desc, String office_address_desc, Integer area_id, Integer level_id) {
        this.office_id = office_id;
        this.office_desc = office_desc;
        this.office_address_desc = office_address_desc;
        this.area_id = area_id;
        this.level_id = level_id;
    }

    public SysOffice(Integer office_id, String office_desc, String office_address_desc, Integer area_id) {
        this.office_id = office_id;
        this.office_desc = office_desc;
        this.office_address_desc = office_address_desc;
        this.area_id = area_id;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public Integer getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Integer office_id) {
        this.office_id = office_id;
    }

    public String getOffice_desc() {
        return office_desc;
    }

    public void setOffice_desc(String office_desc) {
        this.office_desc = office_desc;
    }

    public String getOffice_address_desc() {
        return office_address_desc;
    }

    public void setOffice_address_desc(String office_address_desc) {
        this.office_address_desc = office_address_desc;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }
}
