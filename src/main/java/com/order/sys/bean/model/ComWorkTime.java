package com.order.sys.bean.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "com_work_time")
public class ComWorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    @JsonIgnore
    private Integer time_id;

    @Column(name = "office_id")
    @JsonIgnore
    private Integer office_id;

    @Column(name = "sleep_time")
    private Date sleep_time;

    @Column(name = "notice")
    private String notice;

    @Column(name = "reason")
    private String reason;


    public ComWorkTime() {
    }

    public ComWorkTime(Integer office_id, Date sleep_time, String notice, String reason) {
        this.office_id = office_id;
        this.sleep_time = sleep_time;
        this.notice = notice;
        this.reason = reason;
    }

    public ComWorkTime(Integer time_id, Integer office_id, Date sleep_time, String notice, String reason) {
        this.time_id = time_id;
        this.office_id = office_id;
        this.sleep_time = sleep_time;
        this.notice = notice;
        this.reason = reason;
    }

    public Integer getTime_id() {
        return time_id;
    }

    public void setTime_id(Integer time_id) {
        this.time_id = time_id;
    }

    public Integer getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Integer office_id) {
        this.office_id = office_id;
    }

    public Date getSleep_time() {
        return sleep_time;
    }

    public void setSleep_time(Date sleep_time) {
        this.sleep_time = sleep_time;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
