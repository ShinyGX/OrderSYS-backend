package com.order.sys.services;

import com.order.sys.bean.EventNotify;
import org.quartz.Job;

public interface ScheduleServices {

    void addJob(String name, String groupName, String triggerName, String triggerGroupName,
                Class<? extends Job> jobClass, String cron, EventNotify notify);

    void modifyJob(String triggerName,String triggerGroupName,String cron);

    void removeJob(String jobName,String jobGroupName,String triggerName,String triggerGroupName);

    public boolean isExist(String triggerName,String triggerGroupName);
}
