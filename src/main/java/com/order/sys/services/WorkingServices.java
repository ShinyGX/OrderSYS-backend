package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;

public interface WorkingServices {
    BaseMessage<MessageWorking> startWork(Integer accountId, Integer windowsId);
    BaseMessage<String> endWork(Integer accountId);
}
