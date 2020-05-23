package com.order.sys.services;

import com.order.sys.bean.EventNotify;

public interface EvenRemindServices {

    void addOrUpdate(EventNotify notify);
    void delete(EventNotify notify);


}
