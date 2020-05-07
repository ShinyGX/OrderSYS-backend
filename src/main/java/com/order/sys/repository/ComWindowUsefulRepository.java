package com.order.sys.repository;


import com.order.sys.bean.model.ComWindowUseful;
import com.order.sys.bean.model.pk.WindowAccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ComWindowUsefulRepository extends JpaRepository<ComWindowUseful,WindowAccountId> {



    @Query(value = "select * from com_window_useful where window_id=?1",nativeQuery = true)
    ComWindowUseful getByWindowId(Integer windowId);

    @Query(value = "select * from com_window_useful where account_id=?1",nativeQuery = true)
    ComWindowUseful getByToken(Integer token);

    @Transactional
    @Modifying
    @Query(value = "update com_window_useful set is_use=0,account_id=-1 where window_id=?1",nativeQuery = true)
    void releaseWindow(Integer windowId);

    @Transactional
    @Modifying
    @Query(value = "update com_window_useful set is_use=1,account_id=?2 where window_id=?1",nativeQuery = true)
    void lockWindow(Integer windowId,Integer token);

    @Transactional
    @Modifying
    @Query(value = "update com_window_useful set account_id=?2,mission_id=?3 where window_id=?1",nativeQuery = true)
    void setMission(Integer windowId,Integer accountId, Integer missionId);
}
