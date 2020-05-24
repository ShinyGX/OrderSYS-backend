package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;
import com.order.sys.bean.model.ComWorkTime;
import com.order.sys.constants.ErrorCode;
import com.order.sys.services.WorkingServices;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/workTime")
    public BaseMessage<String> workTime(@RequestParam("officeId") Integer officeId,
                                        @RequestParam("time") String time,
                                        @RequestParam("notice") String notice,
                                        @RequestParam("reason") String reason)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            return workingServices.setTime(officeId,date,notice,reason);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return MessageInputUtil.baseMessageErrorInput(ErrorCode.UNKNOWN_ERROR);
    }

    @PostMapping("/getSleepTime")
    public BaseMessage<List<ComWorkTime>> getSleepTime(@RequestParam("token") Integer token,
                                                       @RequestParam("month") Integer month)
    {
        int m = month;
        if(m < 1 || m > 12)
            return MessageInputUtil.baseMessageErrorInput("月份错误",ErrorCode.UNKNOWN_ERROR);

        return workingServices.getTime(token,month);
    }
}
