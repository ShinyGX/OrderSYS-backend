package com.order.sys.repository;


import com.order.sys.bean.dto.MessageRecode;
import com.order.sys.bean.model.SysRecodeStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysRecodeStaffRepository extends JpaRepository<SysRecodeStaff,Integer> {


//    private Integer accountId;
//    private String staffName;
//    private Date time;
//    private String recodeTypeName;
//    private Integer recodeTypeId;
//    private String recodeDetail;
    @Query("select new com.order.sys.bean.dto.MessageRecode(" +
            "a.account_id," +
            "s.staff_name," +
            "r.recode_action_time," +
            "ra.action_desc," +
            "ra.action_id," +
            "r.recode_action_desc)" +
            "from ComAccount a,ComStaff s,SysRecodeStaff r,SysRecodeStaffAction ra " +
            "where a.staff_id=s.staff_id and recode_account_id=a.account_id and ra.action_id=r.recode_action_id")
    List<MessageRecode> queryAll();


}
