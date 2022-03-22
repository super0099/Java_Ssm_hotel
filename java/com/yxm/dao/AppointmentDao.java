package com.yxm.dao;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysIncome;
import com.yxm.po.SysPassenger;
import com.yxm.po.SysRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDao {
    List<SysRoom> selectOrderRoom(Integer appointmentId);
    SysPassenger truePassenger(@Param("PassengerId") String PassengerId, @Param("appointmentId") Integer appointmentId,@Param("PassengerName") String PassengerName);
    Integer verificationPassenger(Integer Id);
    List<SysRoom> selectNurseTypeBerth();
    List<SysPassenger> selectOrderPassenger(Integer appointmentId);
    SysRoom selectRoom(Integer roomId);
    Integer updateRoom(@Param("roomId") Integer roomId,@Param("appointmentId") Integer appointmentId);
    SysAppointment selectAppointmentState(Integer appointmentId);
    Integer updateAppointment(Integer appointmentId);
    Integer updatePassengerData(@Param("passengerId")Integer passengerId,@Param("roomId")Integer roomId);
    Integer updatePassengerDataByRemove(Integer passengerId);
    Integer updateRoomData(SysRoom sysRoom);
    List<SysPassenger> selectRoomDataBySubtract(@Param("roomId") Integer roomId,@Param("appointmentId") Integer appointmentId);
    SysRoom selectRoomByAppId(@Param("appointmentId")Integer appointmentId,@Param("roomId")Integer roomId);
    Integer addIncome(SysIncome sysIncome);
}
