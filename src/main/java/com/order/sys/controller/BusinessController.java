package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageBusiness;
import com.order.sys.bean.dto.ObjCreateBusiness;
import com.order.sys.bean.model.ComBusiness;
import com.order.sys.bean.model.ComBusinessType;
import com.order.sys.services.BusinessServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessServices businessServices;

    @PostMapping("/getBusinessById")
    public BaseMessage<MessageBusiness> getBusinessMessage(
            @RequestParam("token") Integer token,
            @RequestParam("businessId") Integer id)
    {
        return businessServices.getBusinessMsg(token,id);
    }

    @PostMapping("/getAllBusiness")
    public BaseMessage<List<ComBusiness>> getAllBusiness()
    {
        return businessServices.getAllBusiness();
    }


    @PostMapping("/add")
    public BaseMessage<String> addBusiness(@RequestBody ObjCreateBusiness obj)
    {
        return businessServices.addBusiness(obj);
    }

    @PostMapping("/rename")
    public BaseMessage<String> renameBusiness(@RequestParam("token") Integer token,
                                              @RequestParam("id") Integer businessId,
                                              @RequestParam(value = "name",required = false) String name,
                                              @RequestParam(value = "desc",required = false) String desc,
                                              @RequestParam(value = "typeId",required = false) Integer id)
    {
        return businessServices.rename(token,businessId,name,desc,id);
    }

    @PostMapping("/delete")
    public BaseMessage<String> deleteBusiness(@RequestParam("token") Integer token,
                                              @RequestParam("id") Integer businessId)
    {
        return businessServices.deleteBusiness(token,businessId);
    }

    @PostMapping("/type")
    public BaseMessage<List<ComBusinessType>> getAllBusinessType()
    {
        return businessServices.getBusinessType();
    }

    @PostMapping("/getBusiness")
    public BaseMessage<List<ComBusiness>> search(@RequestParam(value = "typeId",required = false) Integer id,
                                                 @RequestParam(value = "name",required = false) String name)
    {
        return businessServices.getBusiness(id,name);
    }

    @PostMapping("/getType")
    public BaseMessage<List<ComBusinessType>> getType(@RequestParam(value = "id",required = false) Integer id,
                                                      @RequestParam(value = "name",required = false) String name)
    {
        return businessServices.getType(id,name);
    }
}
