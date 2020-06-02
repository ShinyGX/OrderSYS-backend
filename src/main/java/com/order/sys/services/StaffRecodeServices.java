package com.order.sys.services;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageRecode;
import com.order.sys.bean.dto.PageMessage;

import java.util.List;

public interface StaffRecodeServices {
    void recodeStaffAction(Integer actionId,Integer accountId,String desc);
    PageMessage<List<MessageRecode>> search(String name, String type, Integer token, Integer pageIndex);


}
