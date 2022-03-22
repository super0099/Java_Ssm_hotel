package com.yxm.dao;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WelcomeDao {
    List<SysAppointment> selectWelcomeData();
    List<SysPassenger> selectWelcomeDataByP();
    Integer passengerCode();
    Integer userNameCode();
    Integer roomCode();
    Integer orderCode();
}
