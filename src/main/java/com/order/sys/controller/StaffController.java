package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageStaff;
import com.order.sys.bean.dto.ObjCreateAccount;
import com.order.sys.services.StaffServices;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffServices staffServices;


    @PostMapping("/login")
    public BaseMessage<MessageStaff> login(@RequestParam("username") String username,
                                           @RequestParam("pwd") String psw)
    {
        return staffServices.login(username,psw);
    }

    @PostMapping("/getStaffInfo")
    public BaseMessage<MessageStaff> getStaffInfo(@RequestParam("token") Integer token)
    {
        return staffServices.getStaffInfo(token);
    }

    @PostMapping("/staff")
    public BaseMessage<List<MessageStaff>> getStaffList(@RequestParam("level") Integer level,
                                                        @RequestParam("areaId") Integer areaId)
    {
        return staffServices.getStaffList(level,areaId);
    }


    @PostMapping("/create")
    public BaseMessage<String> createAccount(@RequestBody ObjCreateAccount obj)
    {
        return staffServices.createAccount(
                obj.getToken(),
                obj.getUsername(),
                obj.getPwd(),
                obj.getName(),
                obj.getLevel(),
                obj.getSex(),
                obj.getCity(),
                obj.getArea(),
                obj.getOffice());
    }


    @PostMapping("/resetPwd")
    public BaseMessage<String> resetPwd(@RequestParam("token") Integer staffId,
                                        @RequestParam("oldPwd") String oldPwd,
                                        @RequestParam("newPwd") String newPwd)
    {
        return staffServices.resetPassword(staffId,oldPwd,newPwd);
    }

    @PostMapping("/searchStaff")
    public BaseMessage<List<MessageStaff>> searchStaff(@RequestParam(value = "name",required = false)  String name,
                                                       @RequestParam(value = "role",required = false)  Integer role,
                                                       @RequestParam("levelId") Integer levelId,
                                                       @RequestParam("areaId") Integer areaId)
    {
        return staffServices.searchStaffList(name,role,levelId,areaId);
    }

    @PostMapping("/getStaff")
    public BaseMessage<List<MessageStaff>> getStaff(@RequestParam(value = "name",required = false)  String name,
                                                    @RequestParam(value = "role",required = false)  Integer role,
                                                    @RequestParam("token") Integer token)
    {
        return staffServices.getStaffList(name,role,token);
    }

    @PostMapping("/delete")
    public BaseMessage<String> deleteAccount(
            @RequestParam("token") Integer token,
            @RequestParam("targetToken") Integer staffId)
    {
        return staffServices.deleteAccount(token,staffId);
    }


}
