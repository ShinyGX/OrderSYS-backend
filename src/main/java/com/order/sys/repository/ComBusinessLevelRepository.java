package com.order.sys.repository;

import com.order.sys.bean.dto.internal.MessageOfficeBusinessInternal;
import com.order.sys.bean.model.ComBusinessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ComBusinessLevelRepository extends JpaRepository<ComBusinessLevel,Integer> {


    //    private String businessLevel;
    //    private String businessName;
    //    private String businessType;
//    @Query(value = "select new com.order.sys.bean.dto.internal.MessageOfficeBusinessInternal(bl.business_level_desc," +
//            "b.business_desc,bt.business_type_desc) " +
//            "from ComBusiness b,ComBusinessType bt,ComBusinessLevel bl,ComBusinessLevelLink blt " +
//            "where bl.business_level_id>=?1 and bl.business_level_id=blt.business_id and blt.business_id=b.business_id " +
//            "and b.business_type_id=bt.business_type_id")
    @Query(value = "SELECT bl.business_level_desc,b.business_desc,bt.business_type_desc FROM " +
            "com_business_level as bl, com_business_level_link as blt,com_business as b,com_business_type as bt " +
            "WHERE bl.business_level_id >= ?1 and " +
            "bl.business_level_id=blt.business_level_id and " +
            "blt.business_id=b.business_id and " +
            "b.business_type_id=bt.business_type_id",nativeQuery = true)
    List<Map<String,Object>> getBusinessList(Integer level);
}
