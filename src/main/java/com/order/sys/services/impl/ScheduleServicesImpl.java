package com.order.sys.services.impl;

import com.order.sys.bean.EventNotify;
import com.order.sys.services.ScheduleServices;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServicesImpl implements ScheduleServices {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(String name, String groupName, String triggerName, String triggerGroupName,
                       Class<? extends Job> jobClass, String cron, EventNotify notify) {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(name, groupName).build();
        if (notify != null) {
            jobDetail.getJobDataMap().put("data", notify);
        }

        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();

        triggerBuilder.withIdentity(triggerName, triggerGroupName);
        triggerBuilder.startNow();

        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));

        CronTrigger trigger = (CronTrigger) triggerBuilder.build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            if (!scheduler.isShutdown())
                scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyJob(String triggerName, String triggerGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName);
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(jobName,jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExist(String triggerName, String triggerGroupName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName);
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(trigger != null)
                return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
