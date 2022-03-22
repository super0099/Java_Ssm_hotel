package com.yxm.po;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SysAppointment implements Serializable {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer passengerRoom;
    private Integer passengerAmount;
    private byte presentState;
    private String passengerName;
    private String passengerId;
    private String orderNumber;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPassengerRoom() {
        return passengerRoom;
    }

    public void setPassengerRoom(Integer passengerRoom) {
        this.passengerRoom = passengerRoom;
    }

    public Integer getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(Integer passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public byte getPresentState() {
        return presentState;
    }

    public void setPresentState(byte presentState) {
        this.presentState = presentState;
    }

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
