package com.order.sys.repository;



import com.order.sys.bean.model.SysOfficeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysOfficeLevelRepository extends JpaRepository<SysOfficeLevel,Integer> {

    @Query(value = "select * from sys_office_level where office_level_id>=?1",nativeQuery = true)
    List<SysOfficeLevel> findGreaterLevel(Integer level);
}
