package com.order.sys.services;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageSms;

public interface SmsServices {
    BaseMessage<MessageSms> getCode(String phone);
}
