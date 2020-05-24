package com.order.sys.services;


import com.order.sys.bean.dto.*;
import com.order.sys.bean.model.SysArea;
import com.order.sys.bean.model.SysCity;
import com.order.sys.bean.model.SysOffice;
import com.order.sys.bean.model.SysOfficeLevel;

import java.util.List;

public interface OfficeServices {

    BaseMessage<List<MessageOffice>> getOffices(Integer token);
    BaseMessage<MessageOffice> getByOfficeId(Integer officeId);
    BaseMessage<List<MessageOffice>> getByAreaOffices(Integer areaId);
    BaseMessage<List<MessageOffice>> getByCityOffices(Integer cityId);
    BaseMessage<List<SysCity>> getCity();
    BaseMessage<List<SysArea>> getArea(Integer cityId);
    BaseMessage<List<SysOffice>> getOffice(Integer areaId);
    BaseMessage<String> addOffice(ObjCreateOffice obj);
    BaseMessage<List<SysOfficeLevel>> getValidateLevel(Integer token);
    BaseMessage<MessageReport> getOfficeReport(Integer token, Integer officeId);
    BaseMessage<MessageOfficeDetail> getOfficeDetail(Integer token,Integer officeId);
    BaseMessage<MessageOfficeUsefulResult> getOfficeUsefulResult(Integer token);
}
