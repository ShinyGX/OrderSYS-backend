package com.order.sys.controller;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageSms;
import com.order.sys.services.SmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {



    @Autowired
    private SmsServices smsServices;

    @GetMapping("/code")
    BaseMessage<MessageSms> getCode(@RequestParam("phone") String phone)
    {
        return smsServices.getCode(phone);
    }



}
