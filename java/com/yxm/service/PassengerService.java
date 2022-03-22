package com.yxm.service;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysEnvironment;
import com.yxm.vo.PassengerDataVo;

import java.util.List;

public interface PassengerService {
    List<SysEnvironment> selectEnvironmentPicture(byte pictureType);
    boolean addAppointment(SysAppointment sysAppointment, List<PassengerDataVo> passengerDataVo);
    boolean selectOrderNumberExist(String userName);
}
