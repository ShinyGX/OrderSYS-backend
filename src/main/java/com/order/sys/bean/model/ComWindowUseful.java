package com.order.sys.bean.model;



import com.order.sys.bean.model.pk.WindowAccountId;

import javax.persistence.*;

@Table(name = "com_window_useful")
@Entity
@IdClass(WindowAccountId.class)
public class ComWindowUseful {

    @Id
    @Column(name = "window_id")
    private Integer window_id;

    @Id
    @Column(name = "account_id")
    private Integer account_id;

    @Column(name = "is_use")
    private Integer is_use;

    @Column(name = "mission_id")
    private Integer mission_id;

    public ComWindowUseful(Integer window_id, Integer account_id, Integer is_use) {
        this.window_id = window_id;
        this.account_id = account_id;
        this.is_use = is_use;
    }

    public ComWindowUseful(Integer window_id, Integer account_id, Integer is_use, Integer mission_id) {
        this.window_id = window_id;
        this.account_id = account_id;
        this.is_use = is_use;
        this.mission_id = mission_id;
    }

    public ComWindowUseful() {
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

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }


    public Integer getIs_use() {
        return is_use;
    }

    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }
}
