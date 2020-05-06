package com.order.sys.repository;


import com.order.sys.bean.dto.MessageBook;
import com.order.sys.bean.dto.internal.MessageBusinessRequestInternal;
import com.order.sys.bean.dto.MessageMission;
import com.order.sys.bean.model.BookMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookMissionRepository extends JpaRepository<BookMission,Integer> {
//
//    private Integer userId;
//    private String userName;
//    private String userIcon;
    @Query(value = "select new com.order.sys.bean.dto.MessageMission(" +
            "m.mission_id," +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc," +
            "m.mission_user_id)" +
            " from BookMission m,ComBusiness b,ComBusinessType bt,BookUser u " +
            " where m.mission_office_id=?1 and m.mission_business_id=b.business_id and b.business_type_id=bt.business_type_id and " +
            " bt.business_type_id=?2 and " +
            " m.mission_time>?3 and m.mission_time<?4 and m.mission_is_done=1 and " +
            " u.user_id=m.mission_user_id")
    MessageMission getNext(Integer officeId, Integer businessTypeId, Date start, Date end);

//    private Integer businessId;
//    private String businessName;
//    private String businessDesc;

    @Query(value = "select new com.order.sys.bean.dto.MessageBook(" +
            "b.business_id," +
            "b.business_desc," +
            "b.business_detail) " +
            " from ComBusiness b,ComBusinessType bt,ComWindows w,ComBusinessWindowLink cwl " +
            " where b.business_id=cwl.com_business_id and cwl.com_business_windows_id=w.window_id and w.office_id=?1" +
            " and b.business_type_id=bt.business_type_id")
    List<MessageBook> getUsefulInfo(Integer officeId);


    @Query(value = "select * from book_mission where mission_time>?1 and mission_time<?2",nativeQuery = true)
    List<BookMission> getMission(Date start,Date end);


    //    private Integer businessId;
//    private String businessDesc;
//    private Integer businessTypeId;
//    private Integer businessTypeDesc;
//    private Integer windowId;
//    private String staffName;
//    private Integer staffId;
//    private String staffSex;
    @Query(value = "select new com.order.sys.bean.dto.internal.MessageBusinessRequestInternal(" +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc," +
            "mal.window_id," +
            "s.staff_name," +
            "s.staff_id," +
            "s.staff_sex)" +
            " from ComBusiness b,ComBusinessType bt,BookMissionAccountLink mal," +
            " ComAccount a,ComStaff s,BookMission m  " +
            " where b.business_id=m.mission_business_id and m.mission_id=?1 and b.business_type_id=bt.business_type_id " +
            " and mal.mission_id=m.mission_id and mal.account_id=a.account_id and a.staff_id=s.staff_id")
    MessageBusinessRequestInternal getBusiness(Integer mission_id);

    @Query(value = "select new com.order.sys.bean.dto.internal.MessageBusinessRequestInternal(" +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc)" +
            " from ComBusiness b,ComBusinessType bt,BookMission m "+
            " where b.business_id=m.mission_business_id and m.mission_id=?1 and b.business_type_id=bt.business_type_id")
    MessageBusinessRequestInternal getBusinessNotDone(Integer mission_id);
}
