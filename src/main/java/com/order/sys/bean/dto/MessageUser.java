package com.order.sys.bean.dto;

public class MessageUser {

    private Integer userId;
    private String userName;
    private String userIcon;
    private String phone;

    public MessageUser() {
    }

    public MessageUser(Integer userId, String userName, String userIcon, String phone) {
        this.userId = userId;
        this.userName = userName;
        this.userIcon = userIcon;
        this.phone = phone;
    }

    public MessageUser(Integer userId, String userName, String userIcon) {
        this.userId = userId;
        this.userName = userName;
        this.userIcon = userIcon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
