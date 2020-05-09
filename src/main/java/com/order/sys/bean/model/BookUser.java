package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "book_user")
public class BookUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;


    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_phone")
    private String user_phone;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_weibo_id")
    private String user_weibo_id;

    @Column(name = "user_icon")
    private String user_icon;

    public BookUser(Integer user_id, String user_name, String user_phone, String user_password, String user_weibo_id, String user_icon) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_password = user_password;
        this.user_weibo_id = user_weibo_id;
        this.user_icon = user_icon;
    }

    public BookUser() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_weibo_id() {
        return user_weibo_id;
    }

    public void setUser_weibo_id(String user_weibo_id) {
        this.user_weibo_id = user_weibo_id;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }
}
