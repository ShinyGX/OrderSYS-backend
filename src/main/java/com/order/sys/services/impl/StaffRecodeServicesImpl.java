package com.order.sys.services.impl;

import com.order.sys.bean.model.SysRecodeStaff;
import com.order.sys.repository.SysRecodeStaffRepository;
import com.order.sys.services.StaffRecodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class StaffRecodeServicesImpl implements StaffRecodeServices {

    @Autowired
    private SysRecodeStaffRepository sysRecodeStaffRepository;

    @Override
    public void recodeStaffAction(Integer actionId, Integer accountId,String desc) {
        sysRecodeStaffRepository.save(new SysRecodeStaff(actionId,accountId,new Date(),desc));
    }
}
