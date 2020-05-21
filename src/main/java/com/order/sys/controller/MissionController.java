package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageBook;
import com.order.sys.bean.dto.MessageMission;
import com.order.sys.bean.dto.MessageMissionUser;
import com.order.sys.services.MissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    private MissionServices missionServices;

    @PostMapping("/getNext")
    public BaseMessage<MessageMission> getNext(@RequestParam("token") Integer token,
                                               @RequestParam("businessTypeId") Integer businessTypeId)
    {
        return missionServices.getNext(token,businessTypeId);
    }

    @PostMapping("/add")
    public BaseMessage<String> addMission(
            @RequestParam("userId") Integer userId,
            @RequestParam("officeId") Integer officeId,
            @RequestParam("businessId") Integer businessId,
            @RequestParam("time") Long time){
        return missionServices.addMission(userId,officeId,businessId,new Date(time));
    }

    @PostMapping("/end")
    public BaseMessage<String> endMission(
            @RequestParam("missionId") Integer missionId,
            @RequestParam("windowId") Integer windowId,
            @RequestParam("token") Integer token){
        return missionServices.endMission(token,missionId,windowId);
    }


    @PostMapping("/getUsefulInfo")
    public BaseMessage<List<MessageBook>> getUsefulInfo(@RequestParam("id") Integer id)
    {
        return missionServices.getUsefulMission(id);
    }

    @GetMapping("/getUserMission")
    public BaseMessage<List<MessageMissionUser>> getUserMission(@RequestParam("id") Integer id)
    {
        return missionServices.getMissionList(id);
    }
}
