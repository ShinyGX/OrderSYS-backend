package com.order.sys.repository;

import com.order.sys.bean.dto.MessageRecode;
import com.order.sys.bean.model.SysRecodeStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SysRecodeStaffActionRepository extends JpaRepository<SysRecodeStaff,Integer> {

    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id and " +
            "ra.action_desc like %?1% and s.staff_city_id=?2")
    List<MessageRecode> queryByNameCity(String name,Integer cityId);


    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id and " +
            "ra.action_desc like %?1% and s.staff_area_id=?2")
    List<MessageRecode> queryByNameArea(String name,Integer areaId);


    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id and " +
            "ra.action_desc like %?1% and s.staff_office_id=?2")
    List<MessageRecode> queryByNameOffice(String name,Integer officeId);


}
