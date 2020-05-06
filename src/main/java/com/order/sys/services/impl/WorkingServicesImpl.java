package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;
import com.order.sys.bean.model.ComWindowUseful;
import com.order.sys.bean.model.ComWorking;
import com.order.sys.bean.model.pk.WindowAccountId;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.ComWindowUsefulRepository;
import com.order.sys.repository.ComWorkingRepository;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.services.WorkingServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkingServicesImpl implements WorkingServices {

    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComWorkingRepository comWorkingRepository;

    @Autowired
    private ComWindowUsefulRepository comWindowUsefulRepository;

    @Override
    public BaseMessage<MessageWorking> startWork(Integer accountId, Integer windowsId) {
        ComWorking comWorking = comWorkingRepository.checkIsWorking(accountId);
        if(comWorking != null)
            endWork(accountId);

        ComWorking comWorking1 = new ComWorking(accountId,windowsId,new Date(),1);
        comWorking1 = comWorkingRepository.save(comWorking1);
        ComWindowUseful comWindowUseful = comWindowUsefulRepository.getByWindowId(windowsId);
        if(comWindowUseful == null)
            return MessageInputUtil.baseMessageErrorInput("Window Not Exist",ErrorCode.OBJECT_NOT_FOUND);
        comWindowUseful.setIs_use(1);
        comWindowUseful.setAccount_id(accountId);
        comWindowUsefulRepository.save(comWindowUseful);
        return MessageInputUtil.baseMessageSimpleInputRecode("Network Error",
                comWorkingRepository.getData(comWorking1.getWorking_id()),
                staffRecodeServices,StaffActionCode.START_WORK,
                comWorking1.getAccount_id(),
                comWorking1.getAccount_id() + "在窗口ID" + windowsId + "办理业务");
    }

    @Override
    public BaseMessage<String> endWork(Integer accountId) {
        ComWorking comWorking = comWorkingRepository.checkIsWorking(accountId);
        if(comWorking == null)
            return MessageInputUtil.baseMessageSuccessInput("success");

        comWorking.setEnd_time(new Date());
        comWorking.setIs_working(0);
        comWorkingRepository.save(comWorking);
        ComWindowUseful comWindowUseful = comWindowUsefulRepository.getByWindowId(comWorking.getWindow_id());
        if(comWindowUseful == null)
            return MessageInputUtil.baseMessageErrorInput("Window Not Exist",ErrorCode.OBJECT_NOT_FOUND);
        comWindowUseful.setIs_use(0);
        comWindowUseful.setAccount_id(-1);
        comWindowUsefulRepository.save(comWindowUseful);
        return MessageInputUtil.baseMessageSimpleInputRecode("","success",staffRecodeServices,
                StaffActionCode.END_WORK,
                accountId,
                accountId + "离开了窗口");
    }
}
