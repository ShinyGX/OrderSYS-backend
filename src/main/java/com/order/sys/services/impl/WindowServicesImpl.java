package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWindow;
import com.order.sys.bean.dto.ObjCreateWindow;
import com.order.sys.bean.dto.ObjResetWindow;
import com.order.sys.bean.model.ComAccount;
import com.order.sys.bean.model.ComStaff;
import com.order.sys.bean.model.ComWindowUseful;
import com.order.sys.bean.model.ComWindows;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.services.WindowServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BaseMessage<String> addWindow(ObjCreateWindow obj) {
        ComStaff comStaff = FindObjUtil.permissionCheck(obj.getToken(),comAccountRepository,comStaffRepository);
        if(comStaff == null || (int)comStaff.getStaff_office_id() != (int)obj.getOfficeId())
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);

        ComWindows comWindows = new ComWindows(obj.getWindowName(),obj.getBusinessTypeId(),obj.getOfficeId());
        comWindows = comWindowsRepository.save(comWindows);
        ComWindowUseful comWindowUseful = new ComWindowUseful(comWindows.getWindow_id(),-1,0);
        comWindowUsefulRepository.save(comWindowUseful);
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
        comWindows = comWindowsRepository.save(comWindows);
        ComWindowUseful comWindowUseful = comWindowUsefulRepository.getByWindowId(comWindows.getWindow_id());
        comWindowUsefulRepository.delete(comWindowUseful);

        return MessageInputUtil.baseMessageSimpleInputRecode("",comWindows,
                staffRecodeServices,StaffActionCode.DELETE_WINDOWS,token,token + "删除了窗口id：" + windowId + "的窗口");
    }

    @Override
    public BaseMessage<List<ComWindows>> getValidateWindow(Integer token) {
        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount == null)
            return MessageInputUtil.baseMessageErrorInput("Account Not Found",ErrorCode.OBJECT_NOT_FOUND);
        ComStaff comStaff = FindObjUtil.findById(comAccount.getStaff_id(),comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput("Staff Not Found",ErrorCode.OBJECT_NOT_FOUND);


        return MessageInputUtil.baseMessageListInput("Network error",
                comWindowsRepository.getValidateWindow(comStaff.getStaff_office_id(),token));
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

        return MessageInputUtil.baseMessageSimpleInputRecode("","success",
                staffRecodeServices,StaffActionCode.RESET_WINDOWS,obj.getToken(),
                obj.getToken() + "修改了营业厅id：" + obj.getOfficeId() + "的窗口信息");

    }
}
