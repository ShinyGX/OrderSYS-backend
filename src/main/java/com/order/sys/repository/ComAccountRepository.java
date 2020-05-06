package com.order.sys.repository;


import com.order.sys.bean.dto.MessageStaff;
import com.order.sys.bean.model.ComAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ComAccountRepository extends JpaRepository<ComAccount,Integer> {


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
            " from ComAccount ac,ComStaff s,ComLevel l,SysCity c,SysArea a,SysOffice o " +
            " where ac.account_username=?1 and ac.account_password=?2 and s.staff_id=ac.staff_id and" +
            " s.staff_city_id=c.city_id and s.staff_area_id=a.area_id and s.staff_office_id=o.office_id " +
            " and ac.account_level_id=l.level_id and ac.account_exist=1")
    MessageStaff login(String username, String password);


    @Query(value = "select * from com_account where account_id=?1 and account_password=?2 and account_exist=1",nativeQuery = true)
    ComAccount getAccount(Integer userId,String pwd);

    @Query(value = "select * from com_account where account_username=?1 and account_exist=1",nativeQuery = true)
    ComAccount getAccount(String username);




}
