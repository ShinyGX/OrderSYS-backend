package com.order.sys.services.impl;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageRecode;
import com.order.sys.bean.dto.PageMessage;
import com.order.sys.bean.model.ComAccount;
import com.order.sys.bean.model.ComStaff;
import com.order.sys.bean.model.SysRecodeStaff;
import com.order.sys.constants.ErrorCode;
import com.order.sys.repository.*;
import com.order.sys.services.StaffRecodeServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class StaffRecodeServicesImpl implements StaffRecodeServices {


    @Autowired
    private ComAccountRepository comAccountRepository;
    @Autowired
    private ComStaffRepository comStaffRepository;

    @Autowired
    private SysRecodeStaffRepository sysRecodeStaffRepository;
    @Autowired
    private SysRecodeStaffActionRepository sysRecodeStaffActionRepository;

    @Autowired
    private SysAreaRepository sysAreaRepository;

    @Override
    public void recodeStaffAction(Integer actionId, Integer accountId,String desc) {
        sysRecodeStaffRepository.save(new SysRecodeStaff(actionId,accountId,new Date(),desc));
    }

    @Override
    public PageMessage<List<MessageRecode>> search(String name, String type, Integer token, Integer pageIndex) {
        ComStaff comStaff = FindObjUtil.permissionCheck(token,comAccountRepository,comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.pageMessageErrorInput(ErrorCode.PERMISSION_DENY);
        if(pageIndex == null || pageIndex <= 0)
            pageIndex = 1;
        boolean isCity = false;
        boolean isArea = false;
        ComAccount comAccount = FindObjUtil.findById(token,comAccountRepository);
        if(comAccount.getAccount_level_id() == 1)
            isCity = true;
        else if(comAccount.getAccount_level_id() == 2)
            isArea = true;

        if((name == null || name.isEmpty()) && (type == null || type.isEmpty()))
        {
            List<MessageRecode> recode;
            if(isCity)
                recode = comStaffRepository.queryByCity(comStaff.getStaff_city_id());
            else if (isArea)
                recode = sysAreaRepository.queryByArea(comStaff.getStaff_area_id());
            else
                recode = comStaffRepository.queryByOffice(comStaff.getStaff_office_id());
            int size = recode.size();
            int maxPage = (size + 19) / 20;
            int p = size  / 20;
            if( p == maxPage)
                maxPage -= 1;
            List<MessageRecode> curMsg = new ArrayList<>();
            int i = 20 * (pageIndex - 1);

            int end = i + 20;
            for(;i< end && i < recode.size();i++)
            {
                curMsg.add(recode.get(i));
            }
            return MessageInputUtil.pageMessageSuccessInput(curMsg,pageIndex,maxPage);
        }

        if(name != null && !name.isEmpty() && (type == null || type.isEmpty()))
        {
            List<MessageRecode> recode;
            if(isCity)
                recode = comStaffRepository.queryByNameCity(comStaff.getStaff_city_id(),name);
            else if (isArea)
                recode = comStaffRepository.queryByNameArea(comStaff.getStaff_area_id(),name);
            else
                recode = comStaffRepository.queryByNameOffice(comStaff.getStaff_office_id(),name);
            int size = recode.size();
            int maxPage = (size + 19) / 20;
            int p = size  / 20;
            if( p == maxPage)
                maxPage -= 1;
            List<MessageRecode> curMsg = new ArrayList<>();
            int i = 20 * (pageIndex - 1);

            int end = i + 20;
            for(;i< end && i < recode.size();i++)
            {
                curMsg.add(recode.get(i));
            }
            return MessageInputUtil.pageMessageSuccessInput(curMsg,pageIndex,maxPage);
        }

        if(name == null || name.isEmpty())
        {
            List<MessageRecode> recode;
            if(isCity)
                recode = sysRecodeStaffActionRepository.queryByNameCity(name, comStaff.getStaff_city_id());
            else if (isArea)
                recode = sysRecodeStaffActionRepository.queryByNameArea(name,comStaff.getStaff_area_id());
            else
                recode = sysRecodeStaffActionRepository.queryByNameOffice(name,comStaff.getStaff_office_id());


            int size = recode.size();
            int maxPage = (size + 19) / 20;
            int p = size  / 20;
            if( p == maxPage)
                maxPage -= 1;
            List<MessageRecode> curMsg = new ArrayList<>();
            int i = 20 * (pageIndex - 1);

            int end = i + 20;
            for(;i< end && i < recode.size();i++)
            {
                curMsg.add(recode.get(i));
            }
            return MessageInputUtil.pageMessageSuccessInput(curMsg,pageIndex,maxPage);
        }


        return MessageInputUtil.pageMessageSuccessInput( sysRecodeStaffRepository.queryAll(),1,1);
    }
}
