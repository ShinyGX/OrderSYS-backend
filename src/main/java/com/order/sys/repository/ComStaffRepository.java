package com.order.sys.repository;


import com.order.sys.bean.dto.MessageRecode;
import com.order.sys.bean.dto.MessageStaff;
import com.order.sys.bean.dto.internal.MessageOfficeInternal;
import com.order.sys.bean.model.ComStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComStaffRepository extends JpaRepository<ComStaff,Integer> {

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where s.staff_name like %?1% and ac.account_level_id>?2 and s.staff_city_id=?3 and" +
            " ac.account_level_id=l.level_id and " +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByNameCity(String name, Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where s.staff_name like %?1% and ac.account_level_id>?2 and s.staff_area_id=?3 and" +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByNameArea(String name, Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where s.staff_name like %?1% and ac.account_level_id>?2 and s.staff_office_id=?3 and" +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByNameOffice(String name, Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where ac.account_level_id=?1 and s.staff_area_id=?2 and " +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByLevelArea(Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where ac.account_level_id=?1 and s.staff_city_id=?2 and " +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByLevelCity(Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where ac.account_level_id=?1 and s.staff_office_id=?2 and " +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByLevelOffice(Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where s.staff_name=?1 and ac.account_level_id=?2 and s.staff_area_id=?3 and" +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByLevelAndNameArea(String name, Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where s.staff_name=?1 and ac.account_level_id=?2 and s.staff_city_id=?3 and " +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByLevelAndNameCity(String name, Integer levelId, Integer areaId);

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
            " from ComStaff s,ComLevel l,SysArea a,SysCity c,SysOffice o,ComAccount ac " +
            " where s.staff_name=?1 and ac.account_level_id=?2 and s.staff_office_id=?3 and" +
            " ac.staff_id=s.staff_id and ac.account_exist=1 and " +
            " ac.account_level_id=l.level_id and " +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id")
    List<MessageStaff> getStaffListByLevelAndNameOffice(String name, Integer levelId, Integer areaId);
//
//
//    private String staffId;
//    private String staffName;
//    private String staffSex;
    @Query(value = "select new com.order.sys.bean.dto.internal.MessageOfficeInternal(" +
            "s.staff_id,s.staff_name,s.staff_sex) " +
            "from ComStaff s " +
            "where s.staff_office_id=?1")
    List<MessageOfficeInternal> getStaffList(Integer officeId);



    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "r.recode_action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id " +
            "and s.staff_city_id=?1")
    List<MessageRecode> queryByCity(Integer cityId);
    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "r.recode_action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id " +
            "and s.staff_office_id=?1")
    List<MessageRecode> queryByOffice(Integer office);



    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id and " +
            "s.staff_name like %?2% and s.staff_city_id=?1")
    List<MessageRecode> queryByNameCity(Integer cityId, String name);


    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id and " +
            "s.staff_name like %?2% and s.staff_area_id=?1")
    List<MessageRecode> queryByNameArea(Integer cityId, String name);


    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and r.recode_account_id=a.account_id and ra.action_id=r.recode_action_id and " +
            "s.staff_name like %?2% and s.staff_office_id=?1")
    List<MessageRecode> queryByNameOffice(Integer cityId, String name);


}
