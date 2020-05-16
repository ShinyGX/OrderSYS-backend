package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageUser;
import com.order.sys.bean.model.BookUser;
import com.order.sys.constants.ErrorCode;
import com.order.sys.repository.BookUserRepository;

import com.order.sys.services.UserServices;
import com.order.sys.util.FindObjUtil;
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

    @Override
    public BaseMessage<MessageUser> loginByWeiBo(String weiboId) {
        MessageUser messageUser = bookUserRepository.loginByWeibo(weiboId);
        if(messageUser == null)
        {
            BookUser bookUser = new BookUser();
            bookUser.setUser_weibo_id(weiboId);
            bookUserRepository.save(bookUser);
        }
        messageUser = bookUserRepository.loginByWeibo(weiboId);
        return MessageInputUtil.baseMessageSimpleInput("Database Error",messageUser);
    }

    @Override
    public BaseMessage<MessageUser> reset(Integer id, String name, String phone, String pwd, String icon) {
        BookUser bookUser = FindObjUtil.findById(id,bookUserRepository);
        if(bookUser == null)
        {
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.OBJECT_NOT_FOUND);
        }

        if(name != null)
            bookUser.setUser_name(name);

        if(phone != null)
            bookUser.setUser_phone(phone);

        if(pwd != null)
            bookUser.setUser_password(pwd);

        if(icon != null)
            bookUser.setUser_icon(icon);

        bookUserRepository.save(bookUser);
        return MessageInputUtil.baseMessageSuccessInput(new MessageUser(bookUser.getUser_id(),bookUser.getUser_name(),bookUser.getUser_icon()));
    }


    @Override
    public BaseMessage<MessageUser> register(String phone, String pwd, String name) {

        if(bookUserRepository.findByPhone(phone) != null)
            return MessageInputUtil.baseMessageErrorInput("手机号已存在",ErrorCode.OBJECT_ALREADY_EXIST);

        BookUser user = new BookUser();
        user.setUser_phone(phone);
        user.setUser_name(name);
        user.setUser_password(pwd);
        user = bookUserRepository.save(user);


        return MessageInputUtil.baseMessageSuccessInput(new MessageUser(user.getUser_id(),user.getUser_name(),user.getUser_icon()));
    }

    @Override
    public BaseMessage<MessageUser> userInfo(Integer id) {
        BookUser bookUser = FindObjUtil.findById(id,bookUserRepository);
        if(bookUser == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.OBJECT_NOT_FOUND);

        return MessageInputUtil.baseMessageSuccessInput(new MessageUser(bookUser.getUser_id(),bookUser.getUser_name(),bookUser.getUser_icon()));
    }
}
