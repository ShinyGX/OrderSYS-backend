package com.order.sys.repository;


import com.order.sys.bean.dto.internal.MessageBusinessInternal;
import com.order.sys.bean.model.ComWindows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ComBusinessWindowsRepository extends JpaRepository<ComWindows,Integer> {


    @Query(value = "select new com.order.sys.bean.dto.internal.MessageBusinessInternal(" +
            "o.office_id," +
            "o.office_desc," +
            "o.office_address_desc," +
            "a.area_desc," +
            "c.city_desc," +
            "w.window_id," +
            "w.window_desc) " +
            " from SysOffice o,SysArea a,SysCity c,ComWindows w,ComAccount ac,ComStaff s" +
            " where w.business_type_id=?1 and w.office_id=o.office_id and " +
            " o.area_id=a.area_id and a.city_id=c.city_id and ac.staff_id=s.staff_id and ac.account_level_id>?2 and s.staff_city_id=?3" +
            " and s.staff_city_id=c.city_id")
    List<MessageBusinessInternal> getBusinessMessageInternalCity(Integer businessId, Integer token, Integer cityId);

    @Query(value = "select new com.order.sys.bean.dto.internal.MessageBusinessInternal(" +
            "o.office_id," +
            "o.office_desc," +
            "o.office_address_desc," +
            "a.area_desc," +
            "c.city_desc," +
            "w.window_id," +
            "w.window_desc) " +
            " from SysOffice o,SysArea a,SysCity c,ComWindows w,ComAccount ac,ComStaff s" +
            " where w.business_type_id=?1 and w.office_id=o.office_id and " +
            " o.area_id=a.area_id and a.city_id=c.city_id and ac.staff_id=s.staff_id and ac.account_level_id>?2 and s.staff_area_id=?3" +
            " and s.staff_area_id=a.area_id")
    List<MessageBusinessInternal> getBusinessMessageInternalArea(Integer businessId,Integer token,Integer areaId);

    @Query(value = "select new com.order.sys.bean.dto.internal.MessageBusinessInternal(" +
            "o.office_id," +
            "o.office_desc," +
            "o.office_address_desc," +
            "a.area_desc," +
            "c.city_desc," +
            "w.window_id," +
            "w.window_desc) " +
            " from SysOffice o,SysArea a,SysCity c,ComWindows w,ComAccount ac,ComStaff s " +
            " where w.business_type_id=?1 and w.office_id=o.office_id and " +
            " o.area_id=a.area_id and a.city_id=c.city_id and ac.staff_id=s.staff_id and ac.account_level_id>?2 and s.staff_office_id=?3 " +
            " and s.staff_office_id=o.office_id")
    List<MessageBusinessInternal> getBusinessMessageInternalOffice(Integer businessId,Integer token,Integer officeId);
}
