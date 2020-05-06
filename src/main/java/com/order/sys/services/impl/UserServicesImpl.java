package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageUser;
import com.order.sys.repository.BookUserRepository;

import com.order.sys.services.UserServices;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private BookUserRepository bookUserRepository;

    @Override
    public BaseMessage<MessageUser> login(String phone, String pwd) {
        return MessageInputUtil.baseMessageSimpleInput("账号或密码错误",bookUserRepository.login(phone,pwd));
    }
}
