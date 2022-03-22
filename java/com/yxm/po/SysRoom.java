package com.yxm.po;

import java.io.Serializable;

public class SysRoom implements Serializable {
    private Integer id;
    private String roomName;
    private byte roomState;
    private Integer passengerCount;
    private Integer appointmentId;
    private Integer roomFloor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public byte getRoomState() {
        return roomState;
    }

    public void setRoomState(byte roomState) {
        this.roomState = roomState;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }
}
