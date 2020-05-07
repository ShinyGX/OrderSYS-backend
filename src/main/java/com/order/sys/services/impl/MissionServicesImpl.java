package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageBook;
import com.order.sys.bean.dto.MessageMission;
import com.order.sys.bean.dto.MessageUser;
import com.order.sys.bean.model.*;
import com.order.sys.bean.model.pk.WindowAccountId;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.MissionServices;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MissionServicesImpl implements MissionServices {

    @Autowired
    private BookMissionAccountLinkRepository bookMissionAccountLinkRepository;

    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComWindowUsefulRepository comWindowUsefulRepository;

    @Autowired
    private ComAccountRepository comAccountRepository;
    @Autowired
    private ComStaffRepository comStaffRepository;
    @Autowired
    private BookMissionRepository bookMissionRepository;
    @Autowired
    private BookUserRepository bookUserRepository;

    @Override
    public BaseMessage<MessageMission> getNext(Integer token, Integer businessTypeId) {

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date beginOfDate = cal.getTime();

        cal.add(Calendar.DATE,1);
        Date endOfDate = cal.getTime();

        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Account Not Found",ErrorCode.OBJECT_NOT_FOUND);
        ComStaff comStaff = FindObjUtil.findById(comAccount.getStaff_id(),comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput("Staff Not Found",ErrorCode.OBJECT_NOT_FOUND);

        Integer officeId = comStaff.getStaff_office_id();

        MessageMission messageMission = bookMissionRepository.getNext(officeId,businessTypeId,beginOfDate,endOfDate);
        if(messageMission == null)
            return MessageInputUtil.baseMessageErrorInput("It is last",ErrorCode.UNKNOWN_ERROR);

        BookUser bookUser = FindObjUtil.findById(messageMission.getUserId(),bookUserRepository);
        if(bookUser == null)
            return MessageInputUtil.baseMessageErrorInput("Unknown User",ErrorCode.UNKNOWN_ERROR);

        ComWindowUseful comWindowUseful = comWindowUsefulRepository.getByToken(token);
        comWindowUseful.setMission_id(messageMission.getMissionId());
        comWindowUsefulRepository.save(comWindowUseful);
        messageMission.setCustomerInfo(new MessageUser(bookUser.getUser_id(),bookUser.getUser_name(),bookUser.getUser_icon()));
        return MessageInputUtil.baseMessageSimpleInputRecode("It Is Last",
                messageMission,staffRecodeServices,StaffActionCode.START_MISSION,token,
                "开始处理业务，业务id：" + messageMission.getMissionId());
    }


    @Override
    public BaseMessage<String> addMission(Integer userId, Integer officeId, Integer businessId, Date time) {
        BookMission bookMission = new BookMission(businessId,userId,officeId,time,1);
        bookMissionRepository.save(bookMission);
        return MessageInputUtil.baseMessageSimpleInput("","success");
    }

    @Override
    public BaseMessage<String> endMission(Integer token, Integer missionId,Integer windowId) {
        BookMission mission = FindObjUtil.findById(missionId,bookMissionRepository);
        if(mission == null)
            return MessageInputUtil.baseMessageErrorInput("Mission Not Exist",ErrorCode.OBJECT_NOT_FOUND);

        BookMissionAccountLink bookMissionAccountLink = new BookMissionAccountLink(missionId,token,windowId);
        bookMissionAccountLinkRepository.save(bookMissionAccountLink);

        mission.setMission_is_done(2);
        bookMissionRepository.save(mission);
        return MessageInputUtil.baseMessageSimpleInputRecode("","success",staffRecodeServices, StaffActionCode.END_MISSION,
                token,"结束了业务:"+missionId);
    }

    @Override
    public BaseMessage<List<MessageBook>> getUsefulMission(Integer officeId) {
        List<MessageBook> list = bookMissionRepository.getUsefulInfo(officeId);
        for(MessageBook b : list)
        {
            for(int i = 0;i < 4;i++)
            {
                Calendar cal = Calendar.getInstance();
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

                cal.add(Calendar.DATE,i);
                Date date = cal.getTime();
                b.getUsefulTime().add(date);
            }

        }

        return MessageInputUtil.baseMessageListInput("Network Error",list);
    }
}
