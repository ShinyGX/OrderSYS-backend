package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageBusiness;
import com.order.sys.bean.dto.internal.MessageBusinessInternal;
import com.order.sys.bean.dto.ObjCreateBusiness;
import com.order.sys.bean.model.*;
import com.order.sys.constants.AccountLevel;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.BusinessServices;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServicesImpl implements BusinessServices {

    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComBusinessWindowsRepository comBusinessWindowsRepository;
    @Autowired
    private ComBusinessRepository comBusinessRepository;
    @Autowired
    private ComAccountRepository comAccountRepository;
    @Autowired
    private ComBusinessTypeRepository comBusinessTypeRepository;
    @Autowired
    private ComBusinessWindowsLinkRepository comBusinessWindowsLinkRepository;
    @Autowired
    private ComBusinessLevelLinkRepository comBusinessLevelLinkRepository;
    @Autowired
    private ComStaffRepository comStaffRepository;

    @Autowired
    private SysOfficeRepository sysOfficeRepository;

    @Autowired
    private SysAreaRepository sysAreaRepository;

    @Autowired
    private SysCityRepository sysCityRepository;

    @Override
    public BaseMessage<MessageBusiness> getBusinessMsg(Integer token, Integer businessId) {

        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Token invalid",ErrorCode.OBJECT_NOT_FOUND);
        ComStaff comStaff = FindObjUtil.findById(comAccount.getStaff_id(),comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput("Token invalid",ErrorCode.OBJECT_NOT_FOUND);
        ComBusiness comBusiness = FindObjUtil.findById(businessId,comBusinessRepository);
        if(comBusiness == null)
            return MessageInputUtil.baseMessageErrorInput("Business Not Exist",ErrorCode.OBJECT_NOT_FOUND);


        List<MessageBusinessInternal> internals;
        if(comAccount.getAccount_level_id() == AccountLevel.LEVEL_CITY)
            internals = comBusinessWindowsRepository.getBusinessMessageInternalCity(businessId,token,comStaff.getStaff_city_id());
        else if(comAccount.getAccount_level_id() == AccountLevel.LEVEL_AREA)
            internals = comBusinessWindowsRepository.getBusinessMessageInternalArea(businessId,token,comStaff.getStaff_area_id());
        else
            internals = comBusinessWindowsRepository.getBusinessMessageInternalOffice(businessId,token,comStaff.getStaff_office_id());

        ComBusinessType comBusinessType = FindObjUtil.findById(comBusiness.getBusiness_type_id(),comBusinessTypeRepository);
        if(comBusinessType == null)
            return MessageInputUtil.baseMessageErrorInput("Type Not Exist",ErrorCode.OBJECT_NOT_FOUND);

        MessageBusiness messageBusiness = new MessageBusiness(comBusiness.getBusiness_id(),
                comBusiness.getBusiness_desc(),
                comBusinessType.getBusiness_type_desc(),
                internals);

        return MessageInputUtil.baseMessageSimpleInput("Business Not Exist", messageBusiness);
    }

    @Override
    public BaseMessage<List<ComBusiness>> getAllBusiness() {
        return MessageInputUtil.baseMessageListInput("业务不存在",comBusinessRepository.findAll());
    }

    @Override
    public BaseMessage<List<ComBusiness>> getBusiness(Integer id, String name) {
        if(id == null && name == null)
            return getAllBusiness();

        if(id != null && name == null)
        {
            List<ComBusiness> comBusinesses = new ArrayList<ComBusiness>();
            comBusinesses.add(FindObjUtil.findById(id,comBusinessRepository));
            return MessageInputUtil.baseMessageListInput("业务不存在",
                    comBusinesses);
        }

        if(id == null)
        {
            return MessageInputUtil.baseMessageListInput("业务不存在",
                    comBusinessRepository.findByName(name));
        }

        List<ComBusiness> comBusinesses = comBusinessRepository.findByName(name);
        List<ComBusiness> ret = new ArrayList<>();
        for(ComBusiness b : comBusinesses)
        {
            if(b.getBusiness_type_id() == (int)id)
                ret.add(b);
        }

        return MessageInputUtil.baseMessageListInput("业务不存在",ret);

    }

    @Override
    public BaseMessage<String> addBusiness(ObjCreateBusiness obj) {
        ComAccount comAccount = FindObjUtil.findById(obj.getToken(),comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Token invalid",ErrorCode.OBJECT_NOT_FOUND);
        if(comAccount.getAccount_level_id() != AccountLevel.LEVEL_CITY)
            return MessageInputUtil.baseMessageErrorInput("Permission Deny",ErrorCode.PERMISSION_DENY);

        ComBusiness comBusiness = comBusinessRepository.findByBusinessDesc(obj.getBusinessName());
        if(comBusiness != null)
            return MessageInputUtil.baseMessageErrorInput("Name Exist",ErrorCode.ACTION_ALREADY_DONE);

        ComBusiness comBusiness1 = new ComBusiness(obj.getBusinessName(),obj.getBusinessDetail(),obj.getBusinessTypeId());
        comBusiness1 = comBusinessRepository.save(comBusiness1);
        return MessageInputUtil.baseMessageSimpleInputRecode("","success",
                staffRecodeServices,StaffActionCode.CREATE_BUSINESS,obj.getToken(),
                "创建了业务"+comBusiness1.getBusiness_desc()+"\nid:"+comBusiness1.getBusiness_id());

    }

    @Override
    public BaseMessage<String> rename(Integer token, Integer businessId, String name, String desc,Integer id) {
        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Token invalid",ErrorCode.OBJECT_NOT_FOUND);
        if(comAccount.getAccount_level_id() != AccountLevel.LEVEL_CITY)
            return MessageInputUtil.baseMessageErrorInput("Permission Deny",ErrorCode.PERMISSION_DENY);


        ComBusiness comBusiness = FindObjUtil.findById(businessId,comBusinessRepository);
        if(comBusiness == null)
            return MessageInputUtil.baseMessageErrorInput("Business Not Found",ErrorCode.OBJECT_NOT_FOUND);
        String oldDesc = comBusiness.getBusiness_desc(),oldDetail = comBusiness.getBusiness_detail();
        Integer oldId = comBusiness.getBusiness_type_id();
        String descRecode = "";
        if(name != null)
        {
            descRecode +="\n" + oldDesc + "->" + name;
            comBusiness.setBusiness_desc(name);
        }
        if(desc != null)
        {
            descRecode += "\n" + oldDetail + "->" + desc;
            comBusiness.setBusiness_detail(desc);
        }
        if(id != null)
        {
            descRecode += "\n" + oldId.toString() + "->" + id.toString();
            comBusiness.setBusiness_type_id(id);
        }
        if(descRecode.equals(""))
            descRecode = "\nDo nothing";

        comBusinessRepository.save(comBusiness);
        return MessageInputUtil.baseMessageSimpleInputRecode("","success",staffRecodeServices,
                StaffActionCode.RESET_BUSINESS,token,descRecode);

    }

    @Override
    public BaseMessage<String> deleteBusiness(Integer token, Integer businessId) {
        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Token invalid",ErrorCode.OBJECT_NOT_FOUND);
        ComStaff comStaff = FindObjUtil.findById(comAccount.getStaff_id(),comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput("Token invalid",ErrorCode.OBJECT_NOT_FOUND);
        if(comAccount.getAccount_level_id() != AccountLevel.LEVEL_CITY)
            return MessageInputUtil.baseMessageErrorInput("Permission deny",ErrorCode.PERMISSION_DENY);
        ComBusiness comBusiness = FindObjUtil.findById(businessId,comBusinessRepository);
        if(comBusiness == null)
            return MessageInputUtil.baseMessageErrorInput("Business Id Not Exist",ErrorCode.OBJECT_NOT_FOUND);


        List<ComBusinessWindowLink> comBusinessWindowLinks =
                comBusinessWindowsLinkRepository.findAllByBusinessId(comBusiness.getBusiness_id());
        for(ComBusinessWindowLink obj : comBusinessWindowLinks)
        {
            comBusinessWindowsLinkRepository.deleteById(obj.getId());
        }


        ComBusinessLevelLink comBusinessLevelLink = comBusinessLevelLinkRepository.findByBusinessId(businessId);
        comBusinessLevelLinkRepository.delete(comBusinessLevelLink);

        comBusinessRepository.deleteById(businessId);

        return MessageInputUtil.baseMessageSimpleInputRecode("","success",staffRecodeServices,
                StaffActionCode.DELETE_BUSINESS,token,"删除业务:" + comBusiness.getBusiness_desc());

    }

    @Override
    public BaseMessage<List<ComBusinessType>> getBusinessType() {
        List<ComBusinessType> businessTypeList = comBusinessTypeRepository.findAll();
        return MessageInputUtil.baseMessageListInput("Not Exist",businessTypeList);
    }

    @Override
    public BaseMessage<List<ComBusinessType>> getType(Integer id, String name) {
        if(name == null && id == null)
            return getBusinessType();
        if(name == null)
        {
            List<ComBusinessType> comBusinessTypes = new ArrayList<>();
            comBusinessTypes.add(FindObjUtil.findById(id,comBusinessTypeRepository));
            return MessageInputUtil.baseMessageListInput("无对应类型",comBusinessTypes);
        }

        if(id == null)
        {
            return MessageInputUtil.baseMessageListInput("无对应类型",comBusinessTypeRepository.getType(name));
        }

        return MessageInputUtil.baseMessageErrorInput("???",ErrorCode.UNKNOWN_ERROR);
    }

    @Override
    public BaseMessage<ComBusiness> getBusiness(Integer id) {
        return MessageInputUtil.baseMessageSimpleInput("Obj Not Found",FindObjUtil.findById(id,comBusinessRepository));
    }


}
