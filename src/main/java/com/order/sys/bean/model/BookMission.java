package com.order.sys.bean.model;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_mission")
public class BookMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Integer mission_id;

    @Column(name = "mission_business_id")
    private Integer mission_business_id;

    @Column(name = "mission_user_id")
    private Integer mission_user_id;

    @Column(name = "mission_office_id")
    private Integer mission_office_id;


    @Column(name = "mission_time")
    private Date mission_time;

    @Column(name = "mission_is_done")
    private Integer mission_is_done;

    public BookMission(Integer mission_id, Integer mission_business_id, Integer mission_user_id, Integer mission_office_id, Date mission_time, Integer mission_is_done) {
        this.mission_id = mission_id;
        this.mission_business_id = mission_business_id;
        this.mission_user_id = mission_user_id;
        this.mission_office_id = mission_office_id;
        this.mission_time = mission_time;
        this.mission_is_done = mission_is_done;
    }

    public BookMission(Integer mission_business_id, Integer mission_user_id, Integer mission_office_id, Date mission_time, Integer mission_is_done) {
        this.mission_business_id = mission_business_id;
        this.mission_user_id = mission_user_id;
        this.mission_office_id = mission_office_id;
        this.mission_time = mission_time;
        this.mission_is_done = mission_is_done;
    }

    public Date getMission_time() {
        return mission_time;
    }

    public void setMission_time(Date mission_time) {
        this.mission_time = mission_time;
    }

    public Integer getMission_is_done() {
        return mission_is_done;
    }

    public void setMission_is_done(Integer mission_is_done) {
        this.mission_is_done = mission_is_done;
    }

    public Integer getMission_id() {
        return mission_id;
    }

    public void setMission_id(Integer mission_id) {
        this.mission_id = mission_id;
    }

    public Integer getMission_business_id() {
        return mission_business_id;
    }

    public void setMission_business_id(Integer mission_business_id) {
        this.mission_business_id = mission_business_id;
    }

    public Integer getMission_user_id() {
        return mission_user_id;
    }

    public void setMission_user_id(Integer mission_user_id) {
        this.mission_user_id = mission_user_id;
    }

    public Integer getMission_office_id() {
        return mission_office_id;
    }

    public void setMission_office_id(Integer mission_office_id) {
        this.mission_office_id = mission_office_id;
    }

    public BookMission(Integer missionId, Integer missionBusinessId, Integer missionUserId, Integer missionOfficeId) {
        this.mission_id = missionId;
        this.mission_business_id = missionBusinessId;
        this.mission_user_id = missionUserId;
        this.mission_office_id = missionOfficeId;
    }

    public BookMission() {

    }
}
