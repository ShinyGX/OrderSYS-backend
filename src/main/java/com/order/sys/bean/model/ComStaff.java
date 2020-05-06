package com.order.sys.bean.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "com_staff")
public class ComStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staff_id;

    @Column(name = "staff_name")
    private String staff_name;
    @Column(name = "staff_sex")
    private String staff_sex;
    @Column(name = "staff_work_age")
    private String staff_work_age;
    @Column(name = "staff_work_avg_time")
    private Date staff_work_avg_time;
    @Column(name = "staff_area_id")
    private Integer staff_area_id;
    @Column(name = "staff_city_id")
    private Integer staff_city_id;
    @Column(name = "staff_office_id")
    private Integer staff_office_id;
    @Column(name = "staff_create_time")
    private Date staff_create_time;

    public ComStaff(String staff_name, String staff_sex, Integer staff_area_id, Integer staff_city_id, Integer staff_office_id, Date staff_create_time) {
        this.staff_name = staff_name;
        this.staff_sex = staff_sex;
        this.staff_area_id = staff_area_id;
        this.staff_city_id = staff_city_id;
        this.staff_office_id = staff_office_id;
        this.staff_create_time = staff_create_time;
    }

    public ComStaff(String staff_name, String staff_sex, String staff_work_age, Date staff_work_avg_time, Integer staff_area_id, Integer staff_city_id, Integer staff_office_id, Date staff_create_time) {
        this.staff_name = staff_name;
        this.staff_sex = staff_sex;
        this.staff_work_age = staff_work_age;
        this.staff_work_avg_time = staff_work_avg_time;
        this.staff_area_id = staff_area_id;
        this.staff_city_id = staff_city_id;
        this.staff_office_id = staff_office_id;
        this.staff_create_time = staff_create_time;
    }

    public ComStaff(Integer staff_id, String staff_name, String staff_sex, String staff_work_age, Date staff_work_avg_time, Integer staff_area_id, Integer staff_city_id, Integer staff_office_id, Date staff_create_time) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.staff_sex = staff_sex;
        this.staff_work_age = staff_work_age;
        this.staff_work_avg_time = staff_work_avg_time;
        this.staff_area_id = staff_area_id;
        this.staff_city_id = staff_city_id;
        this.staff_office_id = staff_office_id;
        this.staff_create_time = staff_create_time;
    }

    public void setStaff_work_avg_time(Date staff_work_avg_time) {
        this.staff_work_avg_time = staff_work_avg_time;
    }

    public Date getStaff_create_time() {
        return staff_create_time;
    }

    public void setStaff_create_time(Date staff_create_time) {
        this.staff_create_time = staff_create_time;
    }

    public ComStaff() {
    }


    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }



    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_sex() {
        return staff_sex;
    }

    public void setStaff_sex(String staff_sex) {
        this.staff_sex = staff_sex;
    }

    public String getStaff_work_age() {
        return staff_work_age;
    }

    public void setStaff_work_age(String staff_work_age) {
        this.staff_work_age = staff_work_age;
    }

    public Date getStaff_work_avg_time() {
        return staff_work_avg_time;
    }

    public void setStaff_work_avg_time(Timestamp staff_work_avg_time) {
        this.staff_work_avg_time = staff_work_avg_time;
    }

    public Integer getStaff_area_id() {
        return staff_area_id;
    }

    public void setStaff_area_id(Integer staff_area_id) {
        this.staff_area_id = staff_area_id;
    }

    public Integer getStaff_city_id() {
        return staff_city_id;
    }

    public void setStaff_city_id(Integer staff_city_id) {
        this.staff_city_id = staff_city_id;
    }

    public Integer getStaff_office_id() {
        return staff_office_id;
    }

    public void setStaff_office_id(Integer staff_office_id) {
        this.staff_office_id = staff_office_id;
    }
}
