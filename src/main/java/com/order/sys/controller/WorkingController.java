package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;
import com.order.sys.services.WorkingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work")
public class WorkingController {

    @Autowired
    private WorkingServices workingServices;

    @PostMapping("/start")
    public BaseMessage<MessageWorking> start(@RequestParam("token") Integer accountId,
                                             @RequestParam("windowId") Integer windowsId)
    {
        return workingServices.startWork(accountId,windowsId);
    }

    @PostMapping("/end")
    public BaseMessage<String> end(@RequestParam("token") Integer accountId)
    {
        return workingServices.endWork(accountId);
    }
}
