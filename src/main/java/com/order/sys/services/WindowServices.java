package com.order.sys.services;


import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageWindow;
import com.order.sys.bean.dto.ObjCreateWindow;
import com.order.sys.bean.dto.ObjResetWindow;
import com.order.sys.bean.model.ComWindows;

import java.util.List;

public interface WindowServices {

    BaseMessage<String> addWindow(ObjCreateWindow obj);
    BaseMessage<List<MessageWindow>> getWindows(Integer officeId);
    BaseMessage<ComWindows> deleteWindow(Integer token, Integer windowId);
    BaseMessage<List<ComWindows>> getValidateWindow(Integer token);
    BaseMessage<String> reset(ObjResetWindow obj);
}
