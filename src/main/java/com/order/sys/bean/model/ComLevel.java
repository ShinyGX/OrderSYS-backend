package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "com_level")
public class ComLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Integer level_id;

    @Column(name = "level_desc")
    private String level_desc;

    public ComLevel() {
    }

    public ComLevel(Integer level_id, String level_desc) {
        this.level_id = level_id;
        this.level_desc = level_desc;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public String getLevel_desc() {
        return level_desc;
    }

    public void setLevel_desc(String level_desc) {
        this.level_desc = level_desc;
    }
}
