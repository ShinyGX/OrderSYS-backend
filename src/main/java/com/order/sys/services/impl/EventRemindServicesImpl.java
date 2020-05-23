package com.order.sys.services.impl;

import com.order.sys.NotifyJob;
import com.order.sys.bean.EventNotify;
import com.order.sys.services.EvenRemindServices;
import com.order.sys.services.ScheduleServices;
import com.order.sys.util.CronDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRemindServicesImpl implements EvenRemindServices {

    @Autowired
    private ScheduleServices scheduleServices;

    @Override
    public void addOrUpdate(EventNotify notify) {
        int id = notify.getId();
        String triggerName = "trigger_" + id + "_z";
        String triggerGroupName = "trigger_group_job_event";
        if(scheduleServices.isExist(triggerName,triggerGroupName))
        {
            delete(notify);
        }

        add(notify);
    }

    @Override
    public void delete(EventNotify notify) {
        int id = notify.getId();
        String cron = CronDateUtil.getCron(notify.getTriggerTime());

        String jobName = "job_" + id + "__z";
        String jobGroupName = "event_job_event";
        String triggerName = "trigger_" + id + "_z";
        String triggerGroupName = "trigger_group_job_event";
        if(!scheduleServices.isExist(triggerName,triggerGroupName))
            return;

        scheduleServices.removeJob(jobName,jobGroupName,triggerName,triggerGroupName);
    }

    private void add(EventNotify notify)
    {
        int id = notify.getId();
        String cron = CronDateUtil.getCron(notify.getTriggerTime());

        String jobName = "job_" + id + "__z";
        String jobGroupName = "event_job_event";
        String triggerName = "trigger_" + id + "_z";
        String triggerGroupName = "trigger_group_job_event";
        scheduleServices.addJob(jobName,jobGroupName,triggerName,triggerGroupName,NotifyJob.class,cron,notify);
    }
}
