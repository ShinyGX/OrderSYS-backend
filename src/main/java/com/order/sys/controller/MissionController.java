package com.order.sys.controller;


import com.order.sys.bean.dto.*;
import com.order.sys.services.MissionServices;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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
    public BaseMessage<MessageMissionAddResult> addMission(
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

    @GetMapping("/cancel")
    public BaseMessage<String> cancel(@RequestParam("id") Integer id)
    {
        return missionServices.cancel(id);
    }


    @GetMapping("/getNotice")
    public BaseMessage<List<MessageMissionNotice>> getNotice(@RequestParam("userId") Integer id)
    {
        return missionServices.getNotice(id);
    }

    @PostMapping("/getAllPass")
    public BaseMessage<List<MessageMission>> getPass(@RequestParam("officeId") Integer officeId)
    {
        Calendar calendar = Calendar.getInstance();
        return missionServices.getAllPassMission(officeId,calendar.getTime());
    }

    @PostMapping("/passToQueue")
    public BaseMessage<String> passToQueue(@RequestParam("missionId") Integer missionId)
    {
        return missionServices.passMissionToQueue(missionId);
    }

    @PostMapping("/reachPass")
    public BaseMessage<List<MessageMission>> reachPass(@RequestParam("officeId") Integer officeId,
                                                       @RequestParam(value = "userName",required = false) String username,
                                                       @RequestParam(value = "businessName",required = false) String businessName)
    {
        return missionServices.reachInPassMission(username,businessName,officeId);
    }
}
