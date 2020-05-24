package com.order.sys.repository;

import com.order.sys.bean.model.ComWorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ComWorkTimeRepository extends JpaRepository<ComWorkTime,Integer> {


    @Query(value = "select * from com_work_time where office_id=?1 and sleep_time>=?2 and sleep_time<=?3",nativeQuery = true)
    List<ComWorkTime> findByTime(Integer officeId, Date startTime,Date endTime);
}
