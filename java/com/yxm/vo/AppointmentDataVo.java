package com.yxm.vo;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;
import com.yxm.po.SysRoom;

import java.util.List;

public class AppointmentDataVo extends SysAppointment {
    private List<SysRoom> roomList;
    private List<SysPassenger> passengerList;

    public List<SysRoom> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<SysRoom> roomList) {
        this.roomList = roomList;
    }

    public List<SysPassenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<SysPassenger> passengerList) {
        this.passengerList = passengerList;
    }
}
