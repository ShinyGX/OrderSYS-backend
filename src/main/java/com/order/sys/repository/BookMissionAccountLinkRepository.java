package com.order.sys.repository;


import com.order.sys.bean.model.BookMissionAccountLink;
import com.order.sys.bean.model.pk.MissionBookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMissionAccountLinkRepository extends JpaRepository<BookMissionAccountLink,MissionBookId> {
}
