package com.order.sys.bean.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "sys_recode_staff")
public class SysRecodeStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recode_id")
    private Integer recode_id;

    @Column(name = "recode_action_id")
    private Integer recode_action_id;

    @Column(name = "recode_account_id")
    private Integer recode_account_id;

    @Column(name = "recode_action_time")
    private Date recode_action_time;

    @Column(name = "recode_action_desc")
    private String recode_action_desc;

    public SysRecodeStaff() {
    }

    public SysRecodeStaff(Integer recode_action_id, Integer recode_account_id, Date recode_action_time, String recode_action_desc) {
        this.recode_action_id = recode_action_id;
        this.recode_account_id = recode_account_id;
        this.recode_action_time = recode_action_time;
        this.recode_action_desc = recode_action_desc;
    }

    public SysRecodeStaff(Integer recode_id, Integer recode_action_id, Integer recode_account_id, Date recode_action_time, String recode_action_desc) {
        this.recode_id = recode_id;
        this.recode_action_id = recode_action_id;
        this.recode_account_id = recode_account_id;
        this.recode_action_time = recode_action_time;
        this.recode_action_desc = recode_action_desc;
    }

    public String getRecode_action_desc() {
        return recode_action_desc;
    }

    public void setRecode_action_desc(String recode_action_desc) {
        this.recode_action_desc = recode_action_desc;
    }

    public SysRecodeStaff(Integer recode_action_id, Integer recode_account_id) {
        this.recode_action_id = recode_action_id;
        this.recode_account_id = recode_account_id;
    }

    public SysRecodeStaff(Integer recode_action_id, Integer recode_staff_id, Date recode_action_time) {
        this.recode_action_id = recode_action_id;
        this.recode_account_id = recode_staff_id;
        this.recode_action_time = recode_action_time;
    }

    public SysRecodeStaff(Integer recode_id, Integer recode_action_id, Integer recode_staff_id, Date recode_action_time) {
        this.recode_id = recode_id;
        this.recode_action_id = recode_action_id;
        this.recode_account_id = recode_staff_id;
        this.recode_action_time = recode_action_time;
    }

    public Integer getRecode_id() {
        return recode_id;
    }

    public void setRecode_id(Integer recode_id) {
        this.recode_id = recode_id;
    }

    public Integer getRecode_action_id() {
        return recode_action_id;
    }

    public void setRecode_action_id(Integer recode_action_id) {
        this.recode_action_id = recode_action_id;
    }

    public Integer getRecode_account_id() {
        return recode_account_id;
    }

    public void setRecode_account_id(Integer recode_account_id) {
        this.recode_account_id = recode_account_id;
    }

    public Date getRecode_action_time() {
        return recode_action_time;
    }

    public void setRecode_action_time(Date recode_action_time) {
        this.recode_action_time = recode_action_time;
    }
}
