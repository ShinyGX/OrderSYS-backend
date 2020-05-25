package com.order.sys.repository;


import com.order.sys.bean.dto.MessageBook;
import com.order.sys.bean.dto.MessageMission;
import com.order.sys.bean.dto.MessageMissionNotice;
import com.order.sys.bean.dto.MessageMissionUser;
import com.order.sys.bean.dto.internal.MessageBusinessRequestInternal;
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
            "m.mission_user_id," +
            "m.mission_register_id)" +
            " from BookMission m,ComBusiness b,ComBusinessType bt,BookUser u " +
            " where m.mission_office_id=?1 and m.mission_business_id=b.business_id and b.business_type_id=bt.business_type_id and " +
            " bt.business_type_id=?2 and " +
            " m.mission_time>?3 and m.mission_time<?4 and m.mission_is_done=1 and " +
            " u.user_id=m.mission_user_id and b.is_delete=1 order by m.mission_register_id")
    List<MessageMission> getNext(Integer officeId, Integer businessTypeId, Date start, Date end);

    @Query(value = "select new com.order.sys.bean.dto.MessageMission(" +
            "m.mission_id," +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc," +
            "m.mission_user_id," +
            "m.mission_register_id)" +
            " from BookMission m,ComBusiness b,ComBusinessType bt,BookUser u " +
            " where m.mission_business_id=b.business_id and b.business_type_id=bt.business_type_id and" +
            " u.user_id=m.mission_user_id and m.mission_id=?1 and b.is_delete=1")
    MessageMission getMissionMessageById(Integer missionId);

//    private Integer businessId;
//    private String businessName;
//    private String businessDesc;

    @Query(value = "select new com.order.sys.bean.dto.MessageBook(" +
            "b.business_id," +
            "b.business_desc," +
            "b.business_detail) " +
            " from ComBusiness b,ComBusinessType bt,ComWindows w,ComBusinessWindowLink cwl " +
            " where b.business_id=cwl.com_business_id and cwl.com_business_windows_id=w.window_id and w.office_id=?1" +
            " and b.business_type_id=bt.business_type_id and b.is_delete=1")
    List<MessageBook> getUsefulInfo(Integer officeId);


    @Query(value = "select * from book_mission where mission_office_id=?1 and mission_time>=?2 and mission_time<=?3",nativeQuery = true)
    List<BookMission> getMission(Integer officeId,Date start,Date end);


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
            " and mal.mission_id=m.mission_id and mal.account_id=a.account_id and a.staff_id=s.staff_id and b.is_delete=1")
    MessageBusinessRequestInternal getBusiness(Integer mission_id);

    @Query(value = "select new com.order.sys.bean.dto.internal.MessageBusinessRequestInternal(" +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc)" +
            " from ComBusiness b,ComBusinessType bt,BookMission m "+
            " where b.business_id=m.mission_business_id and m.mission_id=?1 and b.business_type_id=bt.business_type_id and b.is_delete=1")
    MessageBusinessRequestInternal getBusinessNotDone(Integer mission_id);


//    private Integer id;
//    private String businessName;
//    private String businessType;
//    private String officeName;
//    private String officeAddress;
//    private Date missionTime;
    @Query(value = "select new com.order.sys.bean.dto.MessageMissionUser(" +
            "m.mission_id," +
            "b.business_desc," +
            "bt.business_type_desc," +
            "o.office_desc," +
            "o.office_address_desc," +
            "m.mission_time," +
            "m.mission_is_done) " +
            "from BookMission m,ComBusinessType bt,ComBusiness b,SysOffice o " +
            "where m.mission_user_id=?1 and m.mission_business_id=b.business_id and b.business_type_id=bt.business_type_id " +
            "and m.mission_office_id=o.office_id  and b.is_delete=1 order by m.mission_time desc")
    List<MessageMissionUser> getUserMissionList(Integer userId);


    @Query(value = "select count(*) from book_mission where mission_office_id=?1 and mission_business_id=?2 and " +
            "mission_time>=?3 and mission_time <=?4",nativeQuery = true)
    Integer getCount(Integer officeId,Integer businessId,Date time, Date time2);


    @Query(value = "select * from book_mission where mission_business_id=?1",nativeQuery = true)
    List<BookMission> getByBusinessId(Integer businessId);

    @Query(value = "select * from book_mission where mission_office_id=?1 and mission_time >=?2 " +
            "and mission_time<=?3 and mission_is_done=3",nativeQuery = true)
    List<BookMission> getAllPassMission(Integer officeId,Date startTime,Date endTime);


//        this.missionId = missionId;
//        this.businessId = businessId;
//        this.businessDesc = businessDesc;
//        this.businessTypeId = businessTypeId;
//        this.businessTypeDesc = businessTypeDesc;
//        this.userId = userId;
    @Query(value = "select new com.order.sys.bean.dto.MessageMission(" +
            "m.mission_id," +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc," +
            "u.user_id," +
            "m.mission_register_id) " +
            "from BookMission m,BookUser u,ComBusiness b,ComBusinessType bt " +
            "where m.mission_office_id=?2 and m.mission_user_id=u.user_id and u.user_name like %?1% " +
            "and m.mission_business_id=b.business_id and b.business_type_id=bt.business_type_id and b.is_delete=1 " +
            "and m.mission_time>=?3 and m.mission_time<=?4 and m.mission_is_done=3")
    List<MessageMission> getPassMissionByName(String name,Integer officeId,Date startTime,Date endTime);


    @Query(value = "select new com.order.sys.bean.dto.MessageMission(" +
            "m.mission_id," +
            "b.business_id," +
            "b.business_desc," +
            "bt.business_type_id," +
            "bt.business_type_desc," +
            "u.user_id," +
            "m.mission_register_id) " +
            "from BookMission m,BookUser u,ComBusiness b,ComBusinessType bt " +
            "where m.mission_office_id=?2 and m.mission_user_id=u.user_id " +
            "and m.mission_business_id=b.business_id and b.business_type_id=bt.business_type_id " +
            "and b.business_desc like %?1% and b.is_delete=1 and m.mission_time>=?3 and m.mission_time<=?4 " +
            "and m.mission_is_done=3")
    List<MessageMission> getPassMissionByBusinessName(String name,Integer officeId,Date start,Date end);
//    private Integer missionId;
//    private String userPhone;
//    private String userName;
//    private String officeName;
//    private String officeAddress;
//    private String businessName;
//    private String orderTime;
//    private Integer missionRegisterId;
    @Query(value = "select new com.order.sys.bean.dto.MessageMissionNotice(" +
            "m.mission_id,u.user_phone,u.user_name,o.office_desc,o.office_address_desc,b.business_desc,m.mission_time,m.mission_register_id) " +
            "from BookMission m,BookUser u,SysOffice o,ComBusiness b " +
            "where m.mission_user_id=?1 and m.mission_time>=?2 and m.mission_time<=?3 and " +
            "u.user_id=m.mission_user_id and m.mission_business_id=b.business_id and m.mission_office_id=o.office_id" +
            " and b.is_delete=1")
    List<MessageMissionNotice> getNotice(Integer userId, Date startTime, Date endTime);
}
