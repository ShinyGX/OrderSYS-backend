package com.order.sys.util;

import com.order.sys.bean.model.ComAccount;
import com.order.sys.bean.model.ComStaff;
import com.order.sys.repository.ComAccountRepository;
import com.order.sys.repository.ComStaffRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class FindObjUtil {

    public static <T,PK,Jpa extends JpaRepository<T,PK>> T findById(PK id, Jpa jpa)
    {
        Optional<T> opt = jpa.findById(id);
        return opt.orElse(null);
    }




    public static ComStaff permissionCheck(Integer token, ComAccountRepository comAccountRepository, ComStaffRepository comStaffRepository)
    {
        ComAccount comAccount = findById(token,comAccountRepository);
        if(comAccount == null)
            return null;
        return findById(comAccount.getStaff_id(),comStaffRepository);
    }
}
