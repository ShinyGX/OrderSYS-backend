package com.order.sys.controller;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageValidateWindow;
import com.order.sys.bean.dto.MessageWindow;
import com.order.sys.bean.dto.ObjCreateWindow;
import com.order.sys.bean.model.ComWindows;
import com.order.sys.services.WindowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/windows")
public class WindowController {

    @Autowired
    private WindowServices windowServices;

    @PostMapping("/getValidate")
    public BaseMessage<List<MessageValidateWindow>> getValidateWindow(@RequestParam("token") Integer token)
    {
        return windowServices.getValidateWindow(token);
    }

    @PostMapping("/add")
    public BaseMessage<String> add(@RequestBody ObjCreateWindow obj)
    {
        return windowServices.addWindow(obj);
    }

    @PostMapping("/get")
    public BaseMessage<List<MessageWindow>> get(@RequestParam("officeId") Integer officeId)
    {
        return windowServices.getWindows(officeId);
    }

    @PostMapping("/delete")
    public BaseMessage<ComWindows> delete(
            @RequestParam("token") Integer token,
            @RequestParam("windowId")Integer windowId)
    {
        return windowServices.deleteWindow(token,windowId);
    }



}
