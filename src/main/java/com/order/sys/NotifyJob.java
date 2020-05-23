package com.order.sys;

import com.order.sys.bean.EventNotify;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotifyJob implements Job {

    private SimpleDateFormat dateFormat()
    {
        return new SimpleDateFormat("HH:mm:ss");
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Scheduler scheduler = jobExecutionContext.getScheduler();
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        Job job = jobExecutionContext.getJobInstance();
        String jobStr = job.toString();

        System.out.println(dateFormat().format(new Date()) + "-----" + jobDetail.getKey() + ";" + trigger.getKey());

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        EventNotify notify = (EventNotify) dataMap.get("data");

        if(notify != null)
        {
            System.out.print(notify.toString());
        }
    }
}
