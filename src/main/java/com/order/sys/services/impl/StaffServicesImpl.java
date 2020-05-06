package com.order.sys.services.impl;



import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageStaff;
import com.order.sys.bean.model.*;
import com.order.sys.constants.AccountLevel;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffAccountStatus;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.services.StaffServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServicesImpl implements StaffServices {


    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComAccountRepository comAccountRepository;
    @Autowired
    private ComStaffRepository comStaffRepository;
    @Autowired
    private SysAreaRepository sysAreaRepository;
    @Autowired
    private SysCityRepository sysCityRepository;
    @Autowired
    private SysOfficeRepository sysOfficeRepository;


    @Override
    public BaseMessage<MessageStaff> login(String username, String psw) {

        MessageStaff messageStaff = comAccountRepository.login(username,psw);
        if(messageStaff == null)
            return MessageInputUtil.baseMessageErrorInput("用户名或密码错误",ErrorCode.OBJECT_NOT_FOUND);
        return MessageInputUtil.baseMessageSimpleInputRecode(
                "用户名或密码错误", messageStaff,staffRecodeServices,StaffActionCode.ACCOUNT_LOGIN, messageStaff.getToken()
        ,"登入了\n"+"id:"+messageStaff.getToken());
    }


    @Override
    public BaseMessage<List<MessageStaff>> getStaffList(Integer level, Integer areaId) {
        if(level == AccountLevel.LEVEL_CITY)
        {
            return MessageInputUtil.baseMessageListInput("message not exist",sysCityRepository.getStaffCityLevel(areaId));
        }
        else if(level == AccountLevel.LEVEL_AREA)
        {
            return MessageInputUtil.baseMessageListInput("message not exist",sysAreaRepository.getStaffAreaLevel(areaId));
        }
        else if(level == AccountLevel.LEVEL_OFFICE)
        {
            return MessageInputUtil.baseMessageListInput("message not exist",sysOfficeRepository.getStaffOfficeLevel(areaId));
        }

        return MessageInputUtil.baseMessageErrorInput("permission deny",ErrorCode.PERMISSION_DENY);

    }

    @Override
    public BaseMessage<String> createAccount(Integer accountId,String username, String psw, String name, Integer level, String sex, Integer cityId, Integer areaId, Integer officeId) {
        ComAccount cs = comAccountRepository.getAccount(username);
        if(cs != null)
            return MessageInputUtil.baseMessageErrorInput("Username Exist",ErrorCode.OBJECT_ALREADY_EXIST);

        Optional<SysOffice> optOffice = sysOfficeRepository.findById(officeId);
        if(!optOffice.isPresent())
            return MessageInputUtil.baseMessageErrorInput("Office not exist",ErrorCode.OBJECT_ALREADY_EXIST);

        Optional<SysArea> optArea = sysAreaRepository.findById(optOffice.get().getArea_id());
        if(!optArea.isPresent())
            return MessageInputUtil.baseMessageErrorInput("Area Error",ErrorCode.UNKNOWN_ERROR);
        if((int)optArea.get().getArea_id() != (int)areaId)
            return MessageInputUtil.baseMessageErrorInput("Area Not Exist",ErrorCode.OBJECT_NOT_FOUND);

        Optional<SysCity> optCity = sysCityRepository.findById(optArea.get().getCity_id());
        if(!optCity.isPresent())
            return MessageInputUtil.baseMessageErrorInput("City Error",ErrorCode.UNKNOWN_ERROR);
        if((int)optCity.get().getCity_id() != (int)cityId)
            return MessageInputUtil.baseMessageErrorInput("City Not Exist",ErrorCode.OBJECT_NOT_FOUND);


        ComStaff comStaff = new ComStaff(name,sex,areaId,cityId,officeId,new Date());
        ComStaff res = comStaffRepository.save(comStaff);
        ComAccount comAccount = new ComAccount(username,psw,res.getStaff_id(),StaffAccountStatus.STAFF_ACCOUNT_EXIST,level);
        comAccount = comAccountRepository.save(comAccount);
        return MessageInputUtil.baseMessageSimpleInputRecode("unknown error","success",
                staffRecodeServices,StaffActionCode.CREATE_ACCOUNT,accountId,
                "创建了账号"+"\nid:"+comAccount.getAccount_id());
    }

    @Override
    public BaseMessage<String> resetPassword(Integer userId, String oldPwd, String newPwd) {
        ComAccount comAccount = comAccountRepository.getAccount(userId,oldPwd);
        if(comAccount == null)
        {
            return MessageInputUtil.baseMessageErrorInput("Account Not Found",ErrorCode.OBJECT_NOT_FOUND);
        }

        comAccount.setAccount_password(newPwd);
        comAccountRepository.save(comAccount);
        return MessageInputUtil.baseMessageSimpleInputRecode("","Password reset success",
                staffRecodeServices,StaffActionCode.RESET_PASSWORD,userId,
        "重置了密码"+"\nid:"+comAccount.getAccount_id());
    }

    @Override
    public BaseMessage<List<MessageStaff>> searchStaffList(String name, Integer targetLevel, Integer userLevel, Integer areaId) {
        if (name != null  && targetLevel == null) {
            switch ((int) userLevel) {
                case AccountLevel.LEVEL_CITY:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByNameCity(name, userLevel, areaId));

                case AccountLevel.LEVEL_AREA:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByNameArea(name, userLevel, areaId));

                case AccountLevel.LEVEL_OFFICE:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByNameOffice(name, userLevel, areaId));

            }
        } else if (name == null && targetLevel != null) {
            if ((int) targetLevel == (int) userLevel || (int) targetLevel < (int) userLevel)
                return MessageInputUtil.baseMessageErrorInput("Permission Deny", ErrorCode.PERMISSION_DENY);

            switch ((int) userLevel) {
                case AccountLevel.LEVEL_CITY:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByLevelCity(targetLevel, areaId));

                case AccountLevel.LEVEL_AREA:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByLevelArea(targetLevel, areaId));

                case AccountLevel.LEVEL_OFFICE:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByLevelOffice(targetLevel, areaId));

            }
        } else if (name != null) {
            if ((int) targetLevel == (int) userLevel || (int) targetLevel < (int) userLevel)
                return MessageInputUtil.baseMessageErrorInput("Permission Deny", ErrorCode.PERMISSION_DENY);

            switch ((int) userLevel) {
                case AccountLevel.LEVEL_CITY:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByLevelAndNameCity(name, targetLevel, areaId));

                case AccountLevel.LEVEL_AREA:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByLevelAndNameArea(name, targetLevel, areaId));

                case AccountLevel.LEVEL_OFFICE:
                    return MessageInputUtil.baseMessageListInput("Staff Not Exist",
                            comStaffRepository.getStaffListByLevelAndNameOffice(name, targetLevel, areaId));

            }
        } else {
            return getStaffList(userLevel, areaId);
        }

        return MessageInputUtil.baseMessageErrorInput("Permission Deny",ErrorCode.PERMISSION_DENY);
    }

    @Override
    public BaseMessage<String> deleteAccount(Integer token,Integer staffId) {
        ComAccount master = FindObjUtil.findById(token,comAccountRepository);
        if(master == null)
            return MessageInputUtil.baseMessageErrorInput("Token Not Exist",ErrorCode.PERMISSION_DENY);
        ComStaff staff = FindObjUtil.findById(master.getStaff_id(),comStaffRepository);
        if(staff == null)
            return MessageInputUtil.baseMessageErrorInput("Token Not Exist",ErrorCode.PERMISSION_DENY);
        if(comAccountRepository.findById(staffId).isPresent())
        {
            ComAccount comAccount = comAccountRepository.findById(staffId).get();
            if(comAccount.getAccount_exist() == StaffAccountStatus.STAFF_ACCOUNT_DELETE)
                return MessageInputUtil.baseMessageErrorInput("Account Already Delete",ErrorCode.ACTION_ALREADY_DONE);
            if((int)comAccount.getAccount_level_id() < (int)master.getAccount_level_id() || (int)comAccount.getAccount_level_id() == (int)master.getAccount_level_id())
                return MessageInputUtil.baseMessageErrorInput("Permission Deny",ErrorCode.PERMISSION_DENY);

            comAccount.setAccount_exist(StaffAccountStatus.STAFF_ACCOUNT_DELETE);
            return MessageInputUtil.baseMessageSimpleInputRecode("","Delete Success",
                    staffRecodeServices,StaffActionCode.DELETE_ACCOUNT,token,
                    "删除了账号"+"\nid:"+comAccount.getAccount_id());
        }
        else
            return MessageInputUtil.baseMessageErrorInput("Account Not Found",ErrorCode.OBJECT_NOT_FOUND);

    }

    @Override
    public BaseMessage<MessageStaff> getStaffInfo(Integer token) {
        ComAccount account = FindObjUtil.findById(token,comAccountRepository);
        if(account == null)
            return MessageInputUtil.baseMessageErrorInput("Staff Not Exist",ErrorCode.OBJECT_NOT_FOUND);

        return login(account.getAccount_username(),account.getAccount_password());
    }


}
