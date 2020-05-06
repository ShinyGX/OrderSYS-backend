package com.order.sys.repository;


import com.order.sys.bean.model.ComBusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComBusinessTypeRepository extends JpaRepository<ComBusinessType,Integer> {


    @Query(value = "select * from com_business_type where business_type_desc like %?1%",nativeQuery = true)
    List<ComBusinessType> getType(String name);
}
