package com.order.sys.repository;


import com.order.sys.bean.dto.MessageWindow;
import com.order.sys.bean.model.ComWindows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComWindowsRepository extends JpaRepository<ComWindows,Integer> {

    @Query(value = "select w.window_id,w.window_desc,w.office_id,w.business_type_id,w.is_use " +
            " from com_windows as w right join com_window_useful as u on w.window_id=u.window_id where " +
            " w.office_id=?1 and (u.is_use=0 or (u.is_use=1 and u.account_id=?2)) " +
            " group by w.window_id",nativeQuery = true)
    List<ComWindows> getValidateWindow(Integer officeId,Integer accountId);
//    private Integer windowId;
//    private String windowName;
//    private Integer officeId;
//    private String officeName;
//    private String officeAddress;
//    private Integer businessTypeId;
//    private String businessDesc;
    @Query(value = "select new com.order.sys.bean.dto.MessageWindow(" +
            "w.window_id," +
            "w.window_desc," +
            "o.office_id," +
            "o.office_desc," +
            "o.office_address_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc)" +
            "from ComWindows w,SysOffice o,ComBusinessType bt" +
            " where w.office_id=?1 and w.office_id=o.office_id and w.business_type_id=bt.business_type_id" +
            " and w.is_use=1")
    List<MessageWindow> getOfficeWindowMessage(Integer officeId);






}
