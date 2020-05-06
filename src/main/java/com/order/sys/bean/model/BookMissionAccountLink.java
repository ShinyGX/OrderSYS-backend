package com.order.sys.bean.model;



import com.order.sys.bean.model.pk.MissionBookId;

import javax.persistence.*;

@Entity
@Table(name = "book_mission_account_link")
@IdClass(MissionBookId.class)
public class BookMissionAccountLink {

    @Id
    @Column(name = "mission_id")
    private Integer mission_id;

    @Id
    @Column(name = "account_id")
    private Integer account_id;

    @Id
    @Column(name = "window_id")
    private Integer window_id;

    public BookMissionAccountLink() {
    }

    public BookMissionAccountLink(Integer mission_id, Integer account_id, Integer window_id) {
        this.mission_id = mission_id;
        this.account_id = account_id;
        this.window_id = window_id;
    }

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }

    public Integer getMission_id() {
        return mission_id;
    }

    public void setMission_id(Integer mission_id) {
        this.mission_id = mission_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }
}
