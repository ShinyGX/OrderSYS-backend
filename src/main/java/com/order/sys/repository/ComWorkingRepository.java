package com.order.sys.repository;


import com.order.sys.bean.dto.MessageWorking;
import com.order.sys.bean.model.ComWorking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComWorkingRepository extends JpaRepository<ComWorking,Integer> {

    @Query(value = "select * from com_working where account_id=?1 and is_working=1",nativeQuery = true)
    ComWorking checkIsWorking(Integer accountId);


    @Query(value = "select new com.order.sys.bean.dto.MessageWorking(" +
            "w.working_id," +
            "w.window_id," +
            "cw.window_desc," +
            "wt.business_type_id," +
            "wt.business_type_desc)" +
            " from ComWorking w,ComWindows cw,ComBusinessType wt" +
            " where w.window_id=cw.window_id and cw.business_type_id=wt.business_type_id and w.working_id=?1")
    MessageWorking getData(Integer workingId);




}
