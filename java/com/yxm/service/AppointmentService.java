package com.yxm.service;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;
import com.yxm.po.SysRoom;
import com.yxm.vo.AppointmentDataVo;
import com.yxm.vo.H5SelectVo;

import java.math.BigDecimal;
import java.util.List;

public interface AppointmentService {
    AppointmentDataVo inquireOrder(String orderNumber);
    SysPassenger truePassenger(String PassengerId,Integer appointmentId,String PassengerName);
    boolean verificationPassenger(Integer Id);
    List<H5SelectVo> selectNurseTypeBerth();
    SysRoom selectRoom(Integer roomId);
    boolean updateRoom(Integer roomId,Integer appointmentId);
    List<SysPassenger> inquireRoomPersonnel(Integer roomId);
    List<SysPassenger> selectRoomDataBySubtract(Integer roomId,Integer appointmentId);
    List<SysPassenger> selectOrderPassenger(Integer appointmentId);
    SysAppointment selectAppointmentState(Integer appointmentId);
    boolean updateAppointment(Integer appointmentId, BigDecimal money);
    boolean addRoomPassenger(Integer roomId,String listStr);
    boolean removeRoomPassenger(Integer roomId,String listStr);
    SysRoom selectRoomByAppId(Integer appointmentId,Integer roomId);
}
