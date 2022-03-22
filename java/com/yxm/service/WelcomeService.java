package com.yxm.service;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;

import java.util.List;

public interface WelcomeService {
    List<SysAppointment> selectWelcomeData();
    List<SysPassenger> selectWelcomeDataByP();
    Integer passengerCode();
    Integer userNameCode();
    Integer roomCode();
    Integer orderCode();

}
