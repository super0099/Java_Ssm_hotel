package com.yxm.vo;

import java.io.Serializable;

public class AppointmentSocketVo implements Serializable {
    private Integer id;
    private String startDate;
    private String endDate;
    private Integer passengerRoom;
    private Integer passengerAmount;
    private String presentState;
    private String orderNumber;
    private Integer userId;
    private Integer state;
    private Integer day;
    private String price;
    private Integer ControllerCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getPresentState() {
        return presentState;
    }

    public void setPresentState(String presentState) {
        this.presentState = presentState;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getControllerCode() {
        return ControllerCode;
    }

    public void setControllerCode(Integer controllerCode) {
        ControllerCode = controllerCode;
    }
}
