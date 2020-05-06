package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "sys_recode_staff_action")
public class SysRecodeStaffAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Integer action_id;

    @Column(name = "action_desc")
    private String action_desc;

    public SysRecodeStaffAction() {
    }

    public SysRecodeStaffAction(Integer action_id, String action_desc) {
        this.action_id = action_id;
        this.action_desc = action_desc;
    }

    public Integer getAction_id() {
        return action_id;
    }

    public void setAction_id(Integer action_id) {
        this.action_id = action_id;
    }

    public String getAction_desc() {
        return action_desc;
    }

    public void setAction_desc(String action_desc) {
        this.action_desc = action_desc;
    }
}
