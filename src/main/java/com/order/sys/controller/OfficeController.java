package com.order.sys.controller;


import com.order.sys.bean.dto.*;
import com.order.sys.bean.model.SysArea;
import com.order.sys.bean.model.SysCity;
import com.order.sys.bean.model.SysOffice;
import com.order.sys.bean.model.SysOfficeLevel;
import com.order.sys.services.OfficeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeServices officeServices;

    @PostMapping("/getCity")
    public BaseMessage<List<SysCity>> getCity()
    {
        return officeServices.getCity();
    }

    @PostMapping("/getArea")
    public BaseMessage<List<SysArea>> getArea(@RequestParam("cityId") Integer cityId)
    {
        return officeServices.getArea(cityId);
    }

    @PostMapping("/getOffice")
    public BaseMessage<List<SysOffice>> getOffice(@RequestParam("areaId") Integer areaId)
    {
        return officeServices.getOffice(areaId);
    }

    @PostMapping("/getOfficeMessage")
    public BaseMessage<MessageOffice> getOfficeMessage(@RequestParam("officeId") Integer id)
    {
        return officeServices.getByOfficeId(id);
    }

    @PostMapping("/getCityOffices")
    public BaseMessage<List<MessageOffice>> getOfficeMessageByCity(@RequestParam("cityId") Integer id)
    {
        return officeServices.getByCityOffices(id);
    }

    @PostMapping("/getAreaOffices")
    public BaseMessage<List<MessageOffice>> getOfficeMessageByArea(@RequestParam("areaId") Integer id)
    {
        return  officeServices.getByAreaOffices(id);
    }


    @PostMapping("/add")
    public BaseMessage<String> addOffice(@RequestBody ObjCreateOffice objCreateOffice)
    {
        return officeServices.addOffice(objCreateOffice);
    }

    @PostMapping("/requestValidateLevel")
    public BaseMessage<List<SysOfficeLevel>> getValidateLevel(@RequestParam("token") Integer token)
    {
        return officeServices.getValidateLevel(token);
    }

    @PostMapping("/report")
    public BaseMessage<MessageReport> getReport(@RequestParam("token") Integer token,
                                                @RequestParam("officeId") Integer officeId)
    {
        return officeServices.getOfficeReport(token, officeId);
    }
}
