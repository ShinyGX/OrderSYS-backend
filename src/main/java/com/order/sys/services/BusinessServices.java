package com.order.sys.services;



import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageBusiness;
import com.order.sys.bean.dto.ObjCreateBusiness;
import com.order.sys.bean.model.ComBusiness;
import com.order.sys.bean.model.ComBusinessType;

import java.util.List;

public interface BusinessServices {

    BaseMessage<MessageBusiness> getBusinessMsg(Integer token, Integer businessId);
    BaseMessage<List<ComBusiness>> getAllBusiness();
    BaseMessage<List<ComBusiness>> getBusiness(Integer id,String name);
    BaseMessage<String> addBusiness(ObjCreateBusiness obj);
    BaseMessage<String> rename(Integer token,Integer businessId,String name,String desc,Integer id);
    BaseMessage<String> deleteBusiness(Integer token,Integer businessId);
    BaseMessage<List<ComBusinessType>>  getBusinessType();
    BaseMessage<List<ComBusinessType>> getType(Integer id,String name);
    BaseMessage<ComBusiness> getBusiness(Integer id);

}
