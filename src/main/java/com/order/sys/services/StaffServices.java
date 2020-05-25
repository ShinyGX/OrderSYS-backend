package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageStaff;

import java.util.List;

public interface StaffServices {
    BaseMessage<MessageStaff> login(String username, String psw);
    BaseMessage<List<MessageStaff>> getStaffList(Integer level, Integer areaId);
    BaseMessage<String> createAccount(Integer accountId, String username, String psw, String name, Integer level, String sex, Integer cityId, Integer areaId, Integer officeId);
    BaseMessage<String> resetPassword(Integer userId,String oldPwd,String newPwd);
    BaseMessage<List<MessageStaff>> searchStaffList(String name, Integer targetLevel, Integer userLevel, Integer areaId);
    BaseMessage<String> deleteAccount(Integer token,Integer staffId);
    BaseMessage<MessageStaff> getStaffInfo(Integer token);
    BaseMessage<List<MessageStaff>> getStaffList(String name,Integer targetLevel,Integer token);
}
