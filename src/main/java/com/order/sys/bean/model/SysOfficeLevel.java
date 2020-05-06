package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "sys_office_level")
public class SysOfficeLevel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_level_id")
    private Integer office_level_id;

    @Column(name = "office_level_desc")
    private String office_level_desc;

    public SysOfficeLevel() {
    }

    public SysOfficeLevel(Integer office_level_id, String office_level_desc) {
        this.office_level_id = office_level_id;
        this.office_level_desc = office_level_desc;
    }

    public Integer getOffice_level_id() {
        return office_level_id;
    }

    public void setOffice_level_id(Integer office_level_id) {
        this.office_level_id = office_level_id;
    }

    public String getOffice_level_desc() {
        return office_level_desc;
    }

    public void setOffice_level_desc(String office_level_desc) {
        this.office_level_desc = office_level_desc;
    }
}
