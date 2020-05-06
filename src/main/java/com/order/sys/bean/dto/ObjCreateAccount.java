package com.order.sys.bean.dto;


public class ObjCreateAccount {

    private Integer token;
    private String username;
    private String pwd;
    private Integer level;
    private String name;
    private String sex;
    private Integer city;
    private Integer area;

    public ObjCreateAccount(Integer token, String username, String pwd, Integer level, String name, String sex, Integer city, Integer area, Integer office) {
        this.token = token;
        this.username = username;
        this.pwd = pwd;
        this.level = level;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.area = area;
        this.office = office;
    }

    private Integer office;

    public ObjCreateAccount() {
    }

    public ObjCreateAccount(String username, String pwd, Integer level, String name, String sex, Integer city, Integer area, Integer office) {
        this.username = username;
        this.pwd = pwd;
        this.level = level;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.area = area;
        this.office = office;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getOffice() {
        return office;
    }

    public void setOffice(Integer office) {
        this.office = office;
    }
}
