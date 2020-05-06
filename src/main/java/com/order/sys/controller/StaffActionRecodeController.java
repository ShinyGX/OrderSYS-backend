package com.order.sys.controller;


import com.order.sys.services.StaffRecodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffActionRecodeController {

    @Autowired
    private StaffRecodeServices staffRecodeServices;
    void recode(Integer actionId,Integer accountId,String desc) {
        staffRecodeServices.recodeStaffAction(actionId, accountId,desc);
    }
}
