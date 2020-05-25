package com.order.sys.bean.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "com_rule")
public class ComRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_rule_id")
    private Integer com_rule_id;

    @Column(name = "com_rule_name")
    private String com_rule_name;

    @Column(name = "com_rule_text")
    private String com_rule_text;

    @Column(name = "com_office_id")
    @JsonIgnore
    private Integer com_office_id;


    public ComRule() {
    }


    public ComRule(Integer com_rule_id, String com_rule_name, String com_rule_text, Integer com_office_id) {
        this.com_rule_id = com_rule_id;
        this.com_rule_name = com_rule_name;
        this.com_rule_text = com_rule_text;
        this.com_office_id = com_office_id;
    }

    public Integer getCom_office_id() {
        return com_office_id;
    }

    public void setCom_office_id(Integer com_office_id) {
        this.com_office_id = com_office_id;
    }

    public Integer getCom_rule_id() {
        return com_rule_id;
    }

    public void setCom_rule_id(Integer com_rule_id) {
        this.com_rule_id = com_rule_id;
    }

    public String getCom_rule_name() {
        return com_rule_name;
    }

    public void setCom_rule_name(String com_rule_name) {
        this.com_rule_name = com_rule_name;
    }

    public String getCom_rule_text() {
        return com_rule_text;
    }

    public void setCom_rule_text(String com_rule_text) {
        this.com_rule_text = com_rule_text;
    }
}
