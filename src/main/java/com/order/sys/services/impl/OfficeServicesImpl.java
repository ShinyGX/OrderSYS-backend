package com.order.sys.services.impl;


import com.order.sys.bean.dto.*;
import com.order.sys.bean.dto.internal.MessageBusinessRequestInternal;
import com.order.sys.bean.dto.internal.MessageOfficeBusinessInternal;
import com.order.sys.bean.dto.internal.MessageOfficeInternal;
import com.order.sys.bean.model.*;
import com.order.sys.constants.AccountLevel;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.OfficeServices;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfficeServicesImpl implements OfficeServices {


    @Autowired
    private ComBusinessLevelRepository comBusinessLevelRepository;

    @Autowired
    private ComStaffRepository comStaffRepository;

    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComAccountRepository comAccountRepository;

    @Autowired
    private SysOfficeRepository sysOfficeRepository;

    @Autowired
    private SysCityRepository sysCityRepository;

    @Autowired
    private SysAreaRepository sysAreaRepository;

    @Autowired
    private SysOfficeLevelRepository sysOfficeLevelRepository;

    @Autowired
    private BookMissionRepository bookMissionRepository;

    @Autowired
    private ComBusinessRepository comBusinessRepository;

    @Override
    public BaseMessage<MessageOffice> getByOfficeId(Integer officeId) {
        MessageOffice officeMessage = sysOfficeRepository.getOfficeMessage(officeId);
        return MessageInputUtil.baseMessageSimpleInput("Office Id 不存在",officeMessage);
    }

    @Override
    public BaseMessage<List<MessageOffice>> getByAreaOffices(Integer areaId) {
        List<MessageOffice> officeMessageList = sysAreaRepository.getAreaOffices(areaId);
        return MessageInputUtil.baseMessageListInput("该地区不存在营业厅",officeMessageList);
    }

    @Override
    public BaseMessage<List<MessageOffice>> getByCityOffices(Integer cityId) {
        List<MessageOffice> officeMessageList = sysCityRepository.getCityOffices(cityId);
        return MessageInputUtil.baseMessageListInput("该地区不存在营业厅",officeMessageList);
    }

    @Override
    public BaseMessage<List<SysCity>> getCity() {
        return  MessageInputUtil.baseMessageListInput("CityList Not Found",sysCityRepository.getCity());
    }

    @Override
    public BaseMessage<List<SysArea>> getArea(Integer cityId) {
        return MessageInputUtil.baseMessageListInput("Area Not Found",sysAreaRepository.getArea(cityId));
    }

    @Override
    public BaseMessage<List<SysOffice>> getOffice(Integer areaId) {
        return MessageInputUtil.baseMessageListInput("Office Not Found",sysOfficeRepository.getOffices(areaId));
    }

    @Override
    public BaseMessage<String> addOffice(ObjCreateOffice obj) {
        ComAccount comAccount = FindObjUtil.findById(obj.getToken(),comAccountRepository);
        if(comAccount == null || comAccount.getAccount_level_id() == AccountLevel.LEVEL_WORKER)
        {
            return MessageInputUtil.baseMessageErrorInput("Permission Deny",ErrorCode.PERMISSION_DENY);
        }

        SysOffice sysOffice = new SysOffice(obj.getName(),obj.getAddress(),obj.getAreaId(),obj.getLevelId());
        sysOfficeRepository.save(sysOffice);
        return MessageInputUtil.baseMessageSimpleInputRecode("Save Failed", "success",
                staffRecodeServices,obj.getToken(),StaffActionCode.CREATE_OFFICE,
                "TOKEN " + obj.getToken() + ":建立了营业厅");
    }

    @Override
    public BaseMessage<List<SysOfficeLevel>> getValidateLevel(Integer token) {
        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Account Not Found",ErrorCode.OBJECT_NOT_FOUND);


        return MessageInputUtil.baseMessageListInput("Not Found",sysOfficeLevelRepository.findGreaterLevel(comAccount.getAccount_level_id()));

    }

    @Override
    public BaseMessage<MessageReport> getOfficeReport(Integer token, Integer officeId) {

        SysOffice sysOffice = FindObjUtil.findById(officeId,sysOfficeRepository);
        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(sysOffice == null || comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("UnknownError",ErrorCode.UNKNOWN_ERROR);
        if((int)comAccount.getAccount_level_id() == (int)4)
            return MessageInputUtil.baseMessageErrorInput("Permission deny",ErrorCode.PERMISSION_DENY);
        List<MessageEverydayData> messageEverydayDataList = new ArrayList<>();
        for(int i = 0;i < 7;i++)
        {

            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - i , 0, 0, 0);
            Date beginOfDate = cal.getTime();

            cal.add(Calendar.DATE,1);
            Date endOfDate = cal.getTime();


            List<BookMission> bookMissionList = bookMissionRepository.getMission(beginOfDate,endOfDate);

            MessageEverydayData messageEverydayData = new MessageEverydayData();
            messageEverydayData.setTime(beginOfDate);
            messageEverydayData.setPerson(bookMissionList.size());
            List<MessageBusinessRequestInternal> messageBusinessRequestInternalList = new ArrayList<>();
            HashMap<String,Integer> map = new HashMap<>();
            for(BookMission bm: bookMissionList)
            {
                MessageBusinessRequestInternal businessRequestInternal = bookMissionRepository.getBusiness(bm.getMission_id());
                if(businessRequestInternal == null)
                {
                    businessRequestInternal = bookMissionRepository.getBusinessNotDone(bm.getMission_id());
                }
                businessRequestInternal.setDone(bm.getMission_is_done() == 2);
                messageBusinessRequestInternalList.add(businessRequestInternal);
                if(map.containsKey(businessRequestInternal.getBusinessTypeDesc()))
                {
                    map.put(businessRequestInternal.getBusinessTypeDesc(),map.get(businessRequestInternal.getBusinessTypeDesc()) + 1);
                }
                else
                    map.put(businessRequestInternal.getBusinessTypeDesc(),1);
            }

            messageEverydayData.setBusinessTypeValue(map);
            messageEverydayData.setBusinessList(messageBusinessRequestInternalList);
            messageEverydayDataList.add(messageEverydayData);
        }

        HashMap<String,Integer> value = new HashMap<>();
        List<ComBusiness> cbl =  comBusinessRepository.findAll();

        for (ComBusiness cb : cbl)
        {
            value.put(cb.getBusiness_desc(),0);
        }

        for(MessageEverydayData data : messageEverydayDataList) {
            for (String key : data.getBusinessTypeValue().keySet()) {
                value.put(key, data.getBusinessTypeValue().get(key) + 1);
            }
        }

        MessageReport messageReport = new MessageReport(messageEverydayDataList,value);

        return MessageInputUtil.baseMessageSuccessInput(messageReport);
    }

    @Override
    public BaseMessage<MessageOfficeDetail> getOfficeDetail(Integer token, Integer officeId) {
        ComStaff comStaff = FindObjUtil.permissionCheck(token,comAccountRepository,comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);

        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        int level = comAccount.getAccount_level_id();
        boolean isCity = false,isArea = false;
        int office = comStaff.getStaff_office_id();
        if(level == 1 && office == (int)officeId)
            isCity = true;
        if(level == 2 && office == (int)officeId)
            isArea = true;

        List<Map<String,Object>> bll;
        if(isCity)
        {
            bll = comBusinessLevelRepository.getBusinessList(1);
        }
        else if(isArea)
        {
            bll = comBusinessLevelRepository.getBusinessList(2);
        }
        else {
            bll = comBusinessLevelRepository.getBusinessList(3);
        }

        List<MessageOfficeBusinessInternal> obi = new ArrayList<>();
        for(Map<String,Object> map : bll)
        {
            MessageOfficeBusinessInternal messageOfficeBusinessInternal =
                    new MessageOfficeBusinessInternal(
                            (String)map.get("business_level_desc"),
                            (String)map.get("business_desc"),
                            (String)map.get("business_type_desc"));

            obi.add(messageOfficeBusinessInternal);
        }

        List<MessageOfficeInternal> oil = comStaffRepository.getStaffList(officeId);
        SysOffice sysOffice = FindObjUtil.findById(officeId,sysOfficeRepository);
        if(sysOffice == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.OBJECT_NOT_FOUND);

        MessageOfficeDetail messageOfficeDetail = new MessageOfficeDetail();
        messageOfficeDetail.setOfficeDes(sysOffice.getOffice_desc());
        messageOfficeDetail.setOfficeAddress(sysOffice.getOffice_address_desc());
        messageOfficeDetail.setBusinessList(obi);
        messageOfficeDetail.setStaffList(oil);

        return MessageInputUtil.baseMessageSuccessInput(messageOfficeDetail);
    }
}
