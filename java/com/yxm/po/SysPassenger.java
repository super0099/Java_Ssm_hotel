package com.yxm.po;

import java.io.Serializable;

public class SysPassenger implements Serializable {
    private Integer id;
    private String passengerName;
    private String passengerId;
    private Integer roomId;
    private byte verification;
    private Integer appointmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public byte getVerification() {
        return verification;
    }

    public void setVerification(byte verification) {
        this.verification = verification;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
}
