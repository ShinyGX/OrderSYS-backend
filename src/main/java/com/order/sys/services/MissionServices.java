package com.order.sys.services;



import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageBook;
import com.order.sys.bean.dto.MessageMission;
import com.order.sys.bean.dto.MessageMissionUser;

import java.util.Date;
import java.util.List;


public interface MissionServices {

    BaseMessage<MessageMission> getNext(Integer token, Integer businessTypeId);
    BaseMessage<Integer> addMission(Integer userId, Integer officeId, Integer businessId, Date time);
    BaseMessage<String> endMission(Integer token,Integer missionId,Integer windowId);
    BaseMessage<List<MessageBook>> getUsefulMission(Integer officeId);
    BaseMessage<List<MessageMissionUser>> getMissionList(Integer userId);
    BaseMessage<String> cancel(Integer missionId);
}
