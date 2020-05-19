package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;

import java.util.Date;

public interface WorkingServices {
    BaseMessage<MessageWorking> startWork(Integer accountId, Integer windowsId);
    BaseMessage<String> endWork(Integer accountId);
    BaseMessage<String> setTime(Integer officeId, Date time,String notice,String reason);
}
