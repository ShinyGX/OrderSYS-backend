package com.order.sys.repository;


import com.order.sys.bean.dto.MessageUser;
import com.order.sys.bean.model.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookUserRepository extends JpaRepository<BookUser,Integer> {


    @Query(value = "select new com.order.sys.bean.dto.MessageUser(u.user_id,u.user_name,u.user_icon) " +
            " from BookUser u" +
            " where u.user_phone=?1 and u.user_password=?2")
    MessageUser login(String phone, String pwd);

    @Query(value = "select new com.order.sys.bean.dto.MessageUser(u.user_id,u.user_name,u.user_icon) " +
            "from BookUser u " +
            "where u.user_weibo_id=?1")
    MessageUser loginByWeibo(String weiboId);

}
