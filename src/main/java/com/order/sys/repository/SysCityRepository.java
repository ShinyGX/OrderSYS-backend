package com.order.sys.repository;

import com.order.sys.bean.dto.MessageOffice;
import com.order.sys.bean.dto.MessageStaff;
import com.order.sys.bean.model.SysCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysCityRepository extends JpaRepository<SysCity,Integer> {

    @Query(value = "select new com.order.sys.bean.dto.MessageOffice(" +
            "o.office_id," +
            "o.office_desc," +
            "o.office_address_desc," +
            "a.area_id," +
            "a.area_desc," +
            "c.city_id," +
            "c.city_desc) " +
            " from SysOffice o,SysCity c,SysArea a" +
            " where o.area_id=a.area_id and a.city_id=c.city_id and c.city_id=?1")
    List<MessageOffice> getCityOffices(Integer id);

    @Query(value ="select new com.order.sys.bean.dto.MessageStaff(" +
            "ac.account_id," +
            "s.staff_id," +
            "c.city_desc," +
            "a.area_desc," +
            "o.office_desc," +
            "s.staff_name," +
            "s.staff_sex," +
            "s.staff_work_age," +
            "s.staff_work_avg_time," +
            "l.level_id," +
            "l.level_desc," +
            "a.area_id," +
            "c.city_id," +
            "o.office_id) " +
            " from ComStaff s,ComLevel l,SysCity c,SysArea a,SysOffice o,ComAccount ac " +
            " where c.city_id=?1 and ac.account_level_id>1 and" +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id " +
            " and ac.account_level_id=l.level_id and ac.account_exist=1 and ac.staff_id=s.staff_id and" +
            " ac.account_exist=1 and ac.staff_id=s.staff_id")
    List<MessageStaff> getStaffCityLevel(Integer cityId);


    @Query(value = "select * from sys_city",nativeQuery = true)
    List<SysCity> getCity();
}
