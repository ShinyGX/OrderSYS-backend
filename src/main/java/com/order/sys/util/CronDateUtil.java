package com.order.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronDateUtil {

    private static final String CORN_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

    public static String getCron(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(CORN_DATE_FORMAT);
        if(date != null)
        {
            return sdf.format(date);
        }

        return "";
    }

    public static Date getDate(String cron)
    {
        if(cron == null)
            return null;


        SimpleDateFormat sdf = new SimpleDateFormat(CORN_DATE_FORMAT);
        Date date = null;
        try
        {
            date = sdf.parse(cron);
        }catch (ParseException e)
        {
            return null;
        }

        return date;
    }


}
