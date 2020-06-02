package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageRecode;
import com.order.sys.bean.dto.PageMessage;
import com.order.sys.services.StaffRecodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recode")
public class StaffActionRecodeController {

    @Autowired
    private StaffRecodeServices staffRecodeServices;
    void recode(Integer actionId,Integer accountId,String desc) {
        staffRecodeServices.recodeStaffAction(actionId, accountId,desc);
    }

    @PostMapping("/getRecode")
    public PageMessage<List<MessageRecode>> getRecode(@RequestParam(value = "name" ,required = false) String name,
                                                              @RequestParam(value = "type",required = false) String type,
                                                              @RequestParam(value = "token") Integer token,
                                                              @RequestParam(value = "page") Integer pageIndex)
    {
        return staffRecodeServices.search(name,type,token,pageIndex);
    }
}
