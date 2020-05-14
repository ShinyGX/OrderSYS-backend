package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageUser;

public interface UserServices {

    BaseMessage<MessageUser> login(String phone, String pwd);
    BaseMessage<MessageUser> loginByWeiBo(String weiboId);
    BaseMessage<MessageUser> reset(Integer id,String name,String phone,String pwd,String icon);
    BaseMessage<MessageUser> register(String phone,String pwd,String name);
}
