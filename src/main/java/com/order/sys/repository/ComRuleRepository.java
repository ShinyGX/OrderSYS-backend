package com.order.sys.repository;

import com.order.sys.bean.dto.MessageRule;
import com.order.sys.bean.model.ComRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComRuleRepository extends JpaRepository<ComRule,Integer> {


    @Query(value = "select * from com_rule where com_rule_name=?1 and com_office_id=?2",nativeQuery = true)
    ComRule findByName(String name,Integer officeId);


    @Query(value = "select * from com_rule where com_rule_name like %?1% and com_office_id=?2",nativeQuery = true)
    List<ComRule> findAllByName(String name,Integer officeId);


    @Query(value = "select * from com_rule where com_office_id=?1",nativeQuery = true)
    List<ComRule> findAll(Integer officeId);


    @Query(value = "select new com.order.sys.bean.dto.MessageRule(o.office_id,o.office_desc) " +
            "from ComRule r,SysOffice o " +
            "where r.com_office_id=o.office_id group by r.com_office_id")
    List<MessageRule> findAllEachOffice();
}
