package com.order.sys.services.impl;


import com.order.sys.bean.dto.*;
import com.order.sys.bean.model.*;
import com.order.sys.bean.model.pk.WindowAccountId;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.services.WindowServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WindowServicesImpl implements WindowServices {

    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComAccountRepository comAccountRepository;
    @Autowired
    private ComStaffRepository comStaffRepository;
    @Autowired
    private ComWindowsRepository comWindowsRepository;
    @Autowired
    private ComWindowUsefulRepository comWindowUsefulRepository;
    @Autowired
    private ComBusinessRepository comBusinessRepository;
    @Autowired
    private BookMissionRepository bookMissionRepository;

    @Autowired
    private ComBusinessWindowsLinkRepository comBusinessWindowsLinkRepository;

    @Override
    public BaseMessage<String> addWindow(ObjCreateWindow obj) {
        ComStaff comStaff = FindObjUtil.permissionCheck(obj.getToken(),comAccountRepository,comStaffRepository);
        if(comStaff == null || (int)comStaff.getStaff_office_id() != (int)obj.getOfficeId())
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);

        List<ComBusiness> comBusinessList = comBusinessRepository.getByType(obj.getBusinessTypeId());

        ComWindows comWindows = new ComWindows(obj.getWindowName(),obj.getBusinessTypeId(),obj.getOfficeId());
        comWindows = comWindowsRepository.save(comWindows);
        ComWindowUseful comWindowUseful = new ComWindowUseful(comWindows.getWindow_id(),-1,0);
        comWindowUsefulRepository.save(comWindowUseful);

        for(ComBusiness cb : comBusinessList)
        {
            ComBusinessWindowLink comBusinessWindowLink = new ComBusinessWindowLink();
            comBusinessWindowLink.setCom_business_id(cb.getBusiness_id());
            comBusinessWindowLink.setCom_business_windows_id(comWindows.getWindow_id());
            comBusinessWindowsLinkRepository.save(comBusinessWindowLink);
        }

        return MessageInputUtil.baseMessageSimpleInputRecode("","success",
                staffRecodeServices,StaffActionCode.CREATE_WINDOWS,obj.getToken(),
                obj.getToken()+ ":创建了窗口->"+obj.getWindowName() + "\nWindowsId:"+ comWindows.getWindow_id());
    }

    @Override
    public BaseMessage<List<MessageWindow>> getWindows(Integer officeId) {
        List<MessageWindow> messageWindowList = comWindowsRepository.getOfficeWindowMessage(officeId);

        for(MessageWindow mw : messageWindowList)
        {
            mw.setBusinessName(comBusinessRepository.findByType(mw.getBusinessTypeId()));
        }
        return MessageInputUtil.baseMessageListInput("",messageWindowList);
    }

    @Override
    public BaseMessage<ComWindows> deleteWindow(Integer token,Integer windowId) {
        ComStaff comStaff = FindObjUtil.permissionCheck(token,comAccountRepository,comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);
        ComWindows comWindows = FindObjUtil.findById(windowId,comWindowsRepository);
        if(comWindows == null)
            return MessageInputUtil.baseMessageErrorInput("Window Not Found",ErrorCode.OBJECT_NOT_FOUND);
        comWindows.setIs_use(2);

        List<ComBusinessWindowLink> cbwll = comBusinessWindowsLinkRepository.findAllByWindowsId(windowId);
        for(ComBusinessWindowLink cbwl : cbwll)
        {
            comBusinessWindowsLinkRepository.delete(cbwl);
        }

        comWindows = comWindowsRepository.save(comWindows);
        ComWindowUseful comWindowUseful = comWindowUsefulRepository.getByWindowId(comWindows.getWindow_id());
        comWindowUsefulRepository.delete(comWindowUseful);

        return MessageInputUtil.baseMessageSimpleInputRecode("",comWindows,
                staffRecodeServices,StaffActionCode.DELETE_WINDOWS,token,token + "删除了窗口id：" + windowId + "的窗口");
    }

    @Override
    public BaseMessage<List<MessageValidateWindow>> getValidateWindow(Integer token) {
        ComAccount comAccount = FindObjUtil.findById(token, comAccountRepository);
        if (comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Account Not Found", ErrorCode.OBJECT_NOT_FOUND);
        ComStaff comStaff = FindObjUtil.findById(comAccount.getStaff_id(), comStaffRepository);
        if (comStaff == null)
            return MessageInputUtil.baseMessageErrorInput("Staff Not Found", ErrorCode.OBJECT_NOT_FOUND);

        List<ComWindows> comWindowsList = comWindowsRepository.getValidateWindow(comStaff.getStaff_office_id(), token);
        List<MessageValidateWindow> messageValidateWindowList = new ArrayList<>();
        if (comWindowsList.isEmpty()) {
            messageValidateWindowList.add(new MessageValidateWindow(null, false));
        }
        else if (comWindowsList.size() == 1) {
            for (ComWindows comWindows : comWindowsList) {
                WindowAccountId windowAccountId = new WindowAccountId(comWindows.getWindow_id(), token);
                ComWindowUseful comWindowUseful = FindObjUtil.findById(windowAccountId, comWindowUsefulRepository);
                if (comWindowUseful == null)
                    messageValidateWindowList.add(new MessageValidateWindow(comWindows, false));
                else
                {
                    MessageMission messageMission = bookMissionRepository.getMissionMessageById(comWindowUseful.getMission_id());
                    messageValidateWindowList.add(new MessageValidateWindow(comWindows, messageMission,true));
                }

            }
        } else {
            for (ComWindows comWindows : comWindowsList) {
                messageValidateWindowList.add(new MessageValidateWindow(comWindows, false));
            }
        }

        return MessageInputUtil.baseMessageListInput("Network error", messageValidateWindowList);
    }

    @Override
    public BaseMessage<String> reset(ObjResetWindow obj) {
        ComStaff comStaff = FindObjUtil.permissionCheck(obj.getToken(),comAccountRepository,comStaffRepository);
        if(comStaff == null || (int)comStaff.getStaff_office_id() != (int)obj.getOfficeId())
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);


        ComWindows comWindows = FindObjUtil.findById(obj.getWindowId(),comWindowsRepository);
        if(comWindows == null)
            return MessageInputUtil.baseMessageErrorInput("Window Not Found",ErrorCode.OBJECT_NOT_FOUND);

        comWindows.setBusiness_type_id(obj.getBusinessTypeId());
        comWindows.setWindow_desc(obj.getWindowName());
        comWindowsRepository.save(comWindows);


        List<ComBusinessWindowLink> cbwll = comBusinessWindowsLinkRepository.findAllByWindowsId(comWindows.getWindow_id());
        for(ComBusinessWindowLink cbwl : cbwll)
        {
            comBusinessWindowsLinkRepository.delete(cbwl);
        }

        List<ComBusiness> cbl = comBusinessRepository.getByType(obj.getBusinessTypeId());
        for(ComBusiness cb : cbl)
        {
            ComBusinessWindowLink comBusinessWindowLink = new ComBusinessWindowLink();
            comBusinessWindowLink.setCom_business_id(cb.getBusiness_id());
            comBusinessWindowLink.setCom_business_windows_id(obj.getWindowId());
        }

        return MessageInputUtil.baseMessageSimpleInputRecode("","success",
                staffRecodeServices,StaffActionCode.RESET_WINDOWS,obj.getToken(),
                obj.getToken() + "修改了营业厅id：" + obj.getOfficeId() + "的窗口信息");

    }
}
