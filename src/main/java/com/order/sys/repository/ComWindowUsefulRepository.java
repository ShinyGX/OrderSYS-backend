package com.order.sys.repository;


import com.order.sys.bean.model.ComWindowUseful;
import com.order.sys.bean.model.pk.WindowAccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComWindowUsefulRepository extends JpaRepository<ComWindowUseful,WindowAccountId> {



    @Query(value = "select * from com_window_useful where window_id=?1",nativeQuery = true)
    ComWindowUseful getByWindowId(Integer windowId);
}
