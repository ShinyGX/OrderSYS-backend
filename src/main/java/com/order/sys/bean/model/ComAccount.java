package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "com_account")
public class ComAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer account_id;



    public ComAccount(String account_username, String account_password, Integer staff_id) {
        this.account_username = account_username;
        this.account_password = account_password;
        this.staff_id = staff_id;
    }

    @Column(name = "account_username")
    private String account_username;
    @Column(name = "account_password")
    private String account_password;
    @Column(name = "staff_id")
    private Integer staff_id;

    @Column(name = "account_exist")
    private Integer account_exist;

    @Column(name = "account_level_id")
    private Integer account_level_id;


    public ComAccount() {
    }

    public ComAccount(String account_username, String account_password, Integer staff_id, Integer account_exist, Integer account_level_id) {
        this.account_username = account_username;
        this.account_password = account_password;
        this.staff_id = staff_id;
        this.account_exist = account_exist;
        this.account_level_id = account_level_id;
    }

    public Integer getAccount_level_id() {
        return account_level_id;
    }

    public void setAccount_level_id(Integer account_level_id) {
        this.account_level_id = account_level_id;
    }



    public Integer getAccount_exist() {
        return account_exist;
    }

    public void setAccount_exist(Integer account_exist) {
        this.account_exist = account_exist;
    }


    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getAccount_username() {
        return account_username;
    }

    public void setAccount_username(String account_username) {
        this.account_username = account_username;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }
}
