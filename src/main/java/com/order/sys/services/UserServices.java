package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageUser;

public interface UserServices {

    BaseMessage<MessageUser> login(String phone, String pwd);
}
