package com.order.sys.services.impl;


import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.order.sys.bean.dto.*;
import com.order.sys.bean.model.*;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.MissionServices;
import com.order.sys.services.SmsServices;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.services.WorkingServices;
import com.order.sys.util.DateUtils;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MissionServicesImpl implements MissionServices {


    @Autowired
    private SmsServices smsServices;

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
    @Autowired
    private ComBusinessRepository comBusinessRepository;
    @Autowired
    private SysOfficeRepository sysOfficeRepository;

    @Autowired
    private WorkingServices workingServices;

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

        List<MessageMission> messageMission = bookMissionRepository.getNext(officeId,businessTypeId,beginOfDate,endOfDate);
        if(messageMission == null || messageMission.size() == 0)
        {
            return MessageInputUtil.baseMessageErrorInput("这个时段已经没有预约了",ErrorCode.UNKNOWN_ERROR);
        }

        BookMission bookMission = FindObjUtil.findById(messageMission.get(0).getMissionId(),bookMissionRepository);
        bookMission.setMission_is_done(3);
        bookMissionRepository.save(bookMission);


        BookUser bookUser = FindObjUtil.findById(messageMission.get(0).getUserId(),bookUserRepository);
        if(bookUser == null)
            return MessageInputUtil.baseMessageErrorInput("Unknown User",ErrorCode.UNKNOWN_ERROR);

        ComWindowUseful comWindowUseful = comWindowUsefulRepository.getByToken(token);
        comWindowUseful.setMission_id(messageMission.get(0).getMissionId());
        comWindowUsefulRepository.save(comWindowUseful);
        messageMission.get(0).setCustomerInfo(new MessageUser(bookUser.getUser_id(),bookUser.getUser_name(),bookUser.getUser_icon()));
        return MessageInputUtil.baseMessageSimpleInputRecode("It Is Last",
                messageMission.get(0),staffRecodeServices,StaffActionCode.START_MISSION,token,
                "开始处理业务，业务id：" + messageMission.get(0).getMissionId());
    }


    @Override
    public BaseMessage<MessageMissionAddResult> addMission(Integer userId, Integer officeId, Integer businessId, Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        BookMission bookMission = new BookMission(businessId,userId,officeId,time,1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.HOUR_OF_DAY,1);
        int count = bookMissionRepository.getCount(officeId,businessId,time,calendar.getTime());
        bookMission.setMission_register_id(count + 1);
        bookMission = bookMissionRepository.save(bookMission);
        BookUser bookUser = FindObjUtil.findById(userId,bookUserRepository);
        SysOffice sysOffice = FindObjUtil.findById(officeId,sysOfficeRepository);
//        smsServices.returnOrderMessage(bookUser.getUser_phone(),bookUser.getUser_name(),
//                sysOffice.getOffice_desc(),sysOffice.getOffice_address_desc(),
//                String.valueOf(count),sdf.format(time));
        ComBusiness business = FindObjUtil.findById(businessId,comBusinessRepository);

        MessageMissionAddResult missionAddResult = new MessageMissionAddResult(
                bookMission.getMission_id(),
                bookUser.getUser_phone(),
                bookUser.getUser_name(),
                sysOffice.getOffice_desc(),
                sysOffice.getOffice_address_desc(),
                business.getBusiness_desc(),
                sdf.format(time),
                count + 1);
        return MessageInputUtil.baseMessageSuccessInput(missionAddResult);
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
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        List<ComWorkTime> comWorkTimes = workingServices.getTimeByOfficeId(officeId,month);
        for(MessageBook b : list)
        {
            for(int i = 0;i < 4;i++)
            {
                Calendar cal = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.set(Calendar.MINUTE,0);
                cal.set(Calendar.SECOND,0);
                cal.add(Calendar.DATE,i);
                //cal.set(Calendar.HOUR_OF_DAY,9);
                for(int j = 0;j < 1;j++) {
                    cal.set(Calendar.HOUR_OF_DAY,9 + j);
                    if(cal2.getTime().after(cal.getTime()))
                        break;
                    boolean isTheSameDay = false;
                    for(ComWorkTime cwt : comWorkTimes)
                    {
                        Calendar cal3 = Calendar.getInstance();
                        cal3.setTime(cwt.getSleep_time());
                        if(DateUtils.sameDate(cal.getTime(),cal3.getTime()))
                        {
                            b.getUsefulTime().put(cal.getTime(),-1);
                            isTheSameDay = true;
                            break;
                        }
                    }
                    if(isTheSameDay)
                        continue;
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(cal.getTime());
                    cal1.add(Calendar.HOUR_OF_DAY,1);
                    int count = bookMissionRepository.getCount(officeId,b.getBusinessId(),cal.getTime(),cal1.getTime());
                    b.getUsefulTime().put(cal.getTime(),count);
                }

                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.set(Calendar.MINUTE,0);
                cal.set(Calendar.SECOND,0);
                //cal.add(Calendar.HOUR_OF_DAY,14);
                for(int j = 0;j < 1;j++)
                {
                    cal.set(Calendar.HOUR_OF_DAY,14 + j);
                    if(cal2.getTime().after(cal.getTime()))
                        break;
                    boolean isTheSameDay = false;
                    for(ComWorkTime cwt : comWorkTimes)
                    {
                        if(DateUtils.sameDate(cal.getTime(),cwt.getSleep_time()))
                        {
                            b.getUsefulTime().put(cal.getTime(),-1);
                            isTheSameDay = true;
                            break;
                        }
                    }
                    if(isTheSameDay)
                        continue;

                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(cal.getTime());
                    cal1.add(Calendar.HOUR_OF_DAY,1);
                    int count = bookMissionRepository.getCount(officeId,b.getBusinessId(),cal.getTime(),cal1.getTime());
                    b.getUsefulTime().put(cal.getTime(),count);
                }

                //cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//                Date date = cal.getTime();
//                b.getUsefulTime().add(date);
            }

        }

        return MessageInputUtil.baseMessageListInput("该营业厅无业务提供",list);
    }

    @Override
    public BaseMessage<List<MessageMissionUser>> getMissionList(Integer userId) {
        return MessageInputUtil.baseMessageListInput("无预约信息",bookMissionRepository.getUserMissionList(userId));
    }

    @Override
    public BaseMessage<String> cancel(Integer missionId) {
        bookMissionRepository.deleteById(missionId);
        return MessageInputUtil.baseMessageSuccessInput("");
    }

    @Override
    public BaseMessage<MessageMission> back(Integer missionId) {
        BookMission bookMission = FindObjUtil.findById(missionId,bookMissionRepository);
        bookMission.setMission_is_done(1);
        bookMissionRepository.save(bookMission);
        MessageMission messageMission = new MessageMission();
        messageMission.setBusinessId(bookMission.getMission_id());
        return MessageInputUtil.baseMessageSuccessInput(messageMission);
    }

    @Override
    public BaseMessage<List<MessageMission>> getAllPassMission(Integer officeId, Date time) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,12);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        List<BookMission> bookMissions;
        if(time.before(cal.getTime()))
        {
            cal.set(Calendar.HOUR_OF_DAY,8);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Date startTime = cal.getTime();

////TODO : MARK
//            Calendar test = Calendar.getInstance();
//            test.set(Calendar.DAY_OF_MONTH,25);
//            startTime = test.getTime();
            bookMissions = bookMissionRepository.getAllPassMission(officeId,startTime,time);
        }
        else
        {
            cal.set(Calendar.HOUR_OF_DAY,13);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Date startTime = cal.getTime();

//            //TODO : MARK
//            Calendar test = Calendar.getInstance();
//            test.set(Calendar.DAY_OF_MONTH,25);
//            startTime = test.getTime();

            bookMissions = bookMissionRepository.getAllPassMission(officeId,startTime,time);
        }

        List<MessageMission> mml = new ArrayList<>();
        for(BookMission bookMission : bookMissions)
        {
            MessageMission mm = bookMissionRepository.getMissionMessageById(bookMission.getMission_id());
            BookUser bookUser = FindObjUtil.findById(mm.getUserId(),bookUserRepository);
            MessageUser messageUser = new MessageUser(bookUser.getUser_id(),bookUser.getUser_name(),bookUser.getUser_icon(),bookUser.getUser_phone());
            mm.setCustomerInfo(messageUser);
            mml.add(mm);
        }
        return MessageInputUtil.baseMessageSuccessInput(mml);
    }

    @Override
    public BaseMessage<List<MessageMissionNotice>> getNotice(Integer userId) {
        Calendar calendar = Calendar.getInstance();
        Date startTime = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY,2);
        Date endTime = calendar.getTime();
        return MessageInputUtil.baseMessageSuccessInput(bookMissionRepository.getNotice(userId,startTime,endTime));
    }

    @Override
    public BaseMessage<String> passMissionToQueue(Integer missionId) {
        BookMission bookMission = FindObjUtil.findById(missionId,bookMissionRepository);
        if(bookMission == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.OBJECT_NOT_FOUND);
        bookMission.setMission_is_done(1);
        bookMissionRepository.save(bookMission);
        return MessageInputUtil.baseMessageSuccessInput("已返回排队序列");
    }

    @Override
    public BaseMessage<List<MessageMission>> reachInPassMission(String userName, String businessName, Integer officeId) {
        Calendar calendar = Calendar.getInstance();
        if((userName == null || userName.isEmpty() )&&(businessName == null || businessName.isEmpty()))
            return getAllPassMission(officeId,calendar.getTime());

        Date time = calendar.getTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,12);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date startTime;
        if(time.before(cal.getTime())) {
            cal.set(Calendar.HOUR_OF_DAY, 8);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime = cal.getTime();
        }
        else {
            cal.set(Calendar.HOUR_OF_DAY, 13);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime = cal.getTime();
        }

//        //TODO :MARK
//        Calendar test = Calendar.getInstance();
//        test.set(Calendar.DAY_OF_MONTH,25);
//        startTime = test.getTime();

        if(userName != null && !userName.isEmpty()) {
            List<MessageMission> mml = bookMissionRepository.getPassMissionByName(userName, officeId, startTime, calendar.getTime());
            for (MessageMission mm : mml) {
                BookUser bookUser = FindObjUtil.findById(mm.getUserId(), bookUserRepository);
                MessageUser messageUser = new MessageUser();
                messageUser.setPhone(bookUser.getUser_phone());
                messageUser.setUserIcon(bookUser.getUser_icon());
                messageUser.setUserId(messageUser.getUserId());
                messageUser.setUserName(messageUser.getUserName());
                mm.setCustomerInfo(messageUser);
            }

            return MessageInputUtil.baseMessageSuccessInput(mml);
        }


        List<MessageMission> mml = bookMissionRepository.getPassMissionByBusinessName(businessName,officeId,startTime,calendar.getTime());
        for(MessageMission mm : mml)
        {
            BookUser bookUser = FindObjUtil.findById(mm.getUserId(),bookUserRepository);
            MessageUser messageUser = new MessageUser();
            messageUser.setPhone(bookUser.getUser_phone());
            messageUser.setUserIcon(bookUser.getUser_icon());
            messageUser.setUserId(messageUser.getUserId());
            messageUser.setUserName(messageUser.getUserName());
            mm.setCustomerInfo(messageUser);
        }

        return MessageInputUtil.baseMessageSuccessInput(mml);
    }
}
