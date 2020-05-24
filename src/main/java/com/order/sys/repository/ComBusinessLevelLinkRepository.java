package com.order.sys.repository;

import com.order.sys.bean.model.ComBusinessLevelLink;
import com.order.sys.constants.BusinessLevelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComBusinessLevelLinkRepository extends JpaRepository<ComBusinessLevelLink,BusinessLevelId> {


    @Query(value = "select * from com_business_level_link where business_id=?1",nativeQuery = true)
    ComBusinessLevelLink findByBusinessId(Integer businessId);
}
