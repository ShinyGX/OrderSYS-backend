package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;
import com.order.sys.bean.model.ComWorkTime;

import java.util.Date;
import java.util.List;

public interface WorkingServices {
    BaseMessage<MessageWorking> startWork(Integer accountId, Integer windowsId);
    BaseMessage<String> endWork(Integer accountId);
    BaseMessage<String> setTime(Integer officeId, Date time,String notice,String reason);
    BaseMessage<List<ComWorkTime>> getTime(Integer token, Integer month);
    List<ComWorkTime> getTimeByOfficeId(Integer officeId,Integer month);
}
