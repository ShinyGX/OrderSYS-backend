package com.order.sys.repository;



import com.order.sys.bean.model.ComBusinessWindowLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComBusinessWindowsLinkRepository extends JpaRepository<ComBusinessWindowLink,Integer> {


    @Query(value = "select * from com_business_window_link where com_business_id=?1",nativeQuery = true)
    List<ComBusinessWindowLink> findAllByBusinessId(Integer businessId);


}
