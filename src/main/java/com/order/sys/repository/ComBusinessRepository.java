package com.order.sys.repository;


import com.order.sys.bean.dto.internal.MessageOfficeBusinessInternal;
import com.order.sys.bean.model.ComBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComBusinessRepository extends JpaRepository<ComBusiness,Integer> {

    @Query(value = "select * from com_business where business_desc=?1 and is_delete=1",nativeQuery = true)
    ComBusiness findByBusinessDesc(String businessDesc);

    @Query(value = "select * from com_business where business_desc like %?1% and is_delete=1",nativeQuery = true)
    List<ComBusiness> findByName(String name);


    @Query(value = "select business_desc from com_business where business_type_id=?1 and is_delete=1",nativeQuery = true)
    List<String> findByType(Integer typeId);

    @Query(value = "select * from com_business where business_type_id=?1 and is_delete=1",nativeQuery = true)
    List<ComBusiness> getByType(Integer typeId);

}
