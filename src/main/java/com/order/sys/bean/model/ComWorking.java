package com.order.sys.bean.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "com_working")
public class ComWorking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_id")
    private Integer working_id;

    @Column(name = "account_id")
    private Integer account_id;

    @Column(name = "window_id")
    private Integer window_id;

    @Column(name = "start_time")
    private Date start_time;

    @Column(name = "end_time")
    private Date end_time;

    @Column(name = "is_working")
    private Integer is_working;

    public ComWorking(Integer account_id, Integer window_id, Date start_time, Integer is_working) {
        this.account_id = account_id;
        this.window_id = window_id;
        this.start_time = start_time;
        this.is_working = is_working;
    }

    public ComWorking(Integer account_id, Integer window_id, Date start_time, Date end_time, Integer is_working) {
        this.account_id = account_id;
        this.window_id = window_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.is_working = is_working;
    }

    public ComWorking() {
    }

    public ComWorking(Integer working_id, Integer account_id, Integer window_id, Date start_time, Date end_time, Integer is_working) {
        this.working_id = working_id;
        this.account_id = account_id;
        this.window_id = window_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.is_working = is_working;
    }

    public ComWorking(Integer working_id, Integer account_id, Integer window_id, Date start_time, Date end_time) {
        this.working_id = working_id;
        this.account_id = account_id;
        this.window_id = window_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Integer getIs_working() {
        return is_working;
    }

    public void setIs_working(Integer is_working) {
        this.is_working = is_working;
    }

    public Integer getWorking_id() {
        return working_id;
    }

    public void setWorking_id(Integer working_id) {
        this.working_id = working_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
