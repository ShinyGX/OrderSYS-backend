package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageUser;
import com.order.sys.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/login")
    BaseMessage<MessageUser> login(@RequestParam("phone") String phone,
                                   @RequestParam("pwd") String pwd)
    {
        return userServices.login(phone,pwd);
    }

    @GetMapping("/loginByWeibo")
    BaseMessage<MessageUser> loginByWeibo(@RequestParam("weibo") String id)
    {
        return userServices.loginByWeiBo(id);
    }

    @GetMapping("/reset")
    BaseMessage<MessageUser> reset(
            @RequestParam("id") Integer id,
            @RequestParam(value = "phone",required = false) String phone,
            @RequestParam(value = "pwd",required = false) String pwd,
            @RequestParam(value = "icon",required = false) String icon,
            @RequestParam(value = "name",required = false) String name)
    {
        return userServices.reset(id,name,phone,pwd,icon);
    }


    @GetMapping("/register")
    BaseMessage<MessageUser> register(
            @RequestParam("phone") String phone,
            @RequestParam("pwd") String pwd,
            @RequestParam("name")String name)
    {
        return userServices.register(phone,pwd,name);
    }

    @GetMapping("/userInfo")
    BaseMessage<MessageUser> userInfo(@RequestParam("id") Integer id)
    {
        return userServices.userInfo(id);
    }
}
