package com.order.sys.services.impl;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWorking;
import com.order.sys.bean.model.ComStaff;
import com.order.sys.bean.model.ComWindowUseful;
import com.order.sys.bean.model.ComWorkTime;
import com.order.sys.bean.model.ComWorking;
import com.order.sys.constants.ErrorCode;
import com.order.sys.constants.StaffActionCode;
import com.order.sys.repository.*;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.services.WorkingServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WorkingServicesImpl implements WorkingServices {

    @Autowired
    private StaffRecodeServices staffRecodeServices;

    @Autowired
    private ComAccountRepository comAccountRepository;

    @Autowired
    private ComStaffRepository comStaffRepository;

    @Autowired
    private ComWorkingRepository comWorkingRepository;

    @Autowired
    private ComWindowUsefulRepository comWindowUsefulRepository;

    @Autowired
    private ComWorkTimeRepository comWorkTimeRepository;

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
        comWindowUsefulRepository.lockWindow(windowsId,accountId);
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
        comWindowUsefulRepository.releaseWindow(comWindowUseful.getWindow_id());

        return MessageInputUtil.baseMessageSimpleInputRecode("","success",staffRecodeServices,
                StaffActionCode.END_WORK,
                accountId,
                accountId + "离开了窗口");
    }

    @Override
    public BaseMessage<String> setTime(Integer officeId,Date time, String notice, String reason) {
        ComWorkTime cwt = new ComWorkTime(officeId,time,notice,reason);
        comWorkTimeRepository.save(cwt);
        return MessageInputUtil.baseMessageSuccessInput("Success");
    }

    @Override
    public BaseMessage<List<ComWorkTime>> getTime(Integer token, Integer month) {

        ComStaff comStaff = FindObjUtil.permissionCheck(token,comAccountRepository,comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);

        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,month );
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date startDate = cal.getTime();

        cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,month);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,0);

        Date endDate = cal.getTime();

        List<ComWorkTime> cwtl = comWorkTimeRepository.findByTime(comStaff.getStaff_office_id(),startDate,endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(ComWorkTime cwt : cwtl)
        {
            String time = sdf.format(cwt.getSleep_time());
            try {
                cwt.setSleep_time(sdf.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return MessageInputUtil.baseMessageSuccessInput(comWorkTimeRepository.findByTime(comStaff.getStaff_office_id(),startDate,endDate));
    }

    @Override
    public List<ComWorkTime> getTimeByOfficeId(Integer officeId, Integer month) {

        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,month );
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date startDate = cal.getTime();

        cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,month);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,0);

        Date endDate = cal.getTime();

        return comWorkTimeRepository.findByTime(officeId,startDate,endDate);
    }
}
