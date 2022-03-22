package com.yxm.dao;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysEnvironment;
import com.yxm.po.SysPassenger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerDao {
    List<SysEnvironment> selectEnvironmentPicture(byte pictureType);
    Integer addAppointment(SysAppointment sysAppointment);
    Integer addAppointmentRoom(SysPassenger sysPassenger);
    SysAppointment selectOrderNumber(String orderNumber);
    List<SysPassenger> selectOrderPassenger(Integer appointmentId);
    List<SysPassenger> inquireRoomPersonnel(Integer roomId);
}
