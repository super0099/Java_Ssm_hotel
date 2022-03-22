package com.yxm.service.impl;

import com.yxm.dao.AppointmentDao;
import com.yxm.dao.PassengerDao;
import com.yxm.po.SysAppointment;
import com.yxm.po.SysIncome;
import com.yxm.po.SysPassenger;
import com.yxm.po.SysRoom;
import com.yxm.service.AppointmentService;
import com.yxm.vo.AppointmentDataVo;
import com.yxm.vo.H5SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private PassengerDao passengerDao;
    @Autowired
    private AppointmentDao appointmentDao;
    @Override
    public AppointmentDataVo inquireOrder(String orderNumber) {
        AppointmentDataVo appointmentDataVo = new AppointmentDataVo();
        SysAppointment sysAppointment = this.passengerDao.selectOrderNumber(orderNumber);
        List<SysRoom> selectOrderRoom = this.appointmentDao.selectOrderRoom(sysAppointment.getId());
        List<SysPassenger> selectOrderPassenger = this.passengerDao.selectOrderPassenger(sysAppointment.getId());
        appointmentDataVo.setId(sysAppointment.getId());
        appointmentDataVo.setStartDate(sysAppointment.getStartDate());
        appointmentDataVo.setEndDate(sysAppointment.getEndDate());
        appointmentDataVo.setPassengerName(sysAppointment.getPassengerName());
        appointmentDataVo.setPassengerId(sysAppointment.getPassengerId());
        appointmentDataVo.setPassengerAmount(sysAppointment.getPassengerAmount());
        appointmentDataVo.setPassengerRoom(sysAppointment.getPassengerRoom());
        appointmentDataVo.setPresentState(sysAppointment.getPresentState());
        appointmentDataVo.setOrderNumber(sysAppointment.getOrderNumber());
        appointmentDataVo.setPassengerList(selectOrderPassenger);
        appointmentDataVo.setRoomList(selectOrderRoom);
        return appointmentDataVo;
    }

    @Override
    public SysPassenger truePassenger(String PassengerId, Integer appointmentId, String PassengerName) {
        return this.appointmentDao.truePassenger(PassengerId,appointmentId,PassengerName);
    }

    @Override
    public boolean verificationPassenger(Integer Id) {
        return this.appointmentDao.verificationPassenger(Id)>0;
    }

    @Override
    public List<H5SelectVo> selectNurseTypeBerth() {
        List<SysRoom> sysRooms = this.appointmentDao.selectNurseTypeBerth();
        List<H5SelectVo> rList=new ArrayList<>();
        for (SysRoom position:sysRooms) {
            rList.add(new H5SelectVo(String.valueOf(position.getId()),position.getRoomName()));
        }
        return rList;
    }

    @Override
    public SysRoom selectRoom(Integer roomId) {
        return this.appointmentDao.selectRoom(roomId);
    }

    @Override
    public boolean updateRoom(Integer roomId, Integer appointmentId) {
        return this.appointmentDao.updateRoom(roomId,appointmentId)>0;
    }

    @Override
    public List<SysPassenger> inquireRoomPersonnel(Integer roomId) {
        return this.passengerDao.inquireRoomPersonnel(roomId);
    }

    @Override
    public List<SysPassenger> selectRoomDataBySubtract(Integer roomId, Integer appointmentId) {
        return this.appointmentDao.selectRoomDataBySubtract(roomId,appointmentId);
    }

    @Override
    public List<SysPassenger> selectOrderPassenger(Integer appointmentId) {
        return this.appointmentDao.selectOrderPassenger(appointmentId);
    }

    @Override
    public SysAppointment selectAppointmentState(Integer appointmentId) {
        return this.appointmentDao.selectAppointmentState(appointmentId);
    }

    @Override
    public boolean updateAppointment(Integer appointmentId, BigDecimal money) {
        SysIncome sysIncome = new SysIncome();
        sysIncome.setIncomeDate(new Date());
        sysIncome.setIncomeType((byte)1);
        sysIncome.setMoney(money);
        boolean isTrue = this.appointmentDao.addIncome(sysIncome)>0;
        if (isTrue){
            boolean isOk = this.appointmentDao.updateAppointment(appointmentId)>0;
            if (!isOk){
                throw new RuntimeException("数据异常");
            }
        }else {
            throw new RuntimeException("数据异常");
        }
        return true;
    }

    @Override
    public boolean addRoomPassenger(Integer roomId,String listStr) {
        if(listStr.indexOf("[") != -1){
            listStr = listStr.replace("[", "");
        }
        if(listStr.indexOf("]") != -1){
            listStr = listStr.replace("]", "");
        }
        SysRoom sysRoom = this.appointmentDao.selectRoom(roomId);
        String[] str = listStr.split(",");
        sysRoom.setPassengerCount(str.length+sysRoom.getPassengerCount());
        for (int i = 0;i<str.length;i++){
            if(str[i].indexOf("\"") != -1){
                str[i] = str[i].replace("\"", "");
            }
            Integer passengerId = Integer.parseInt(str[i]);
            boolean isOk = this.appointmentDao.updatePassengerData(passengerId,roomId)>0;
            if (isOk!=true){
                throw new RuntimeException("修改旅客信息出错");
            }
        }
        boolean isOk = this.appointmentDao.updateRoomData(sysRoom)>0;
        if (!isOk){
            throw new RuntimeException("修改房间信息出错");
        }
        return true;
    }

    @Override
    public boolean removeRoomPassenger(Integer roomId, String listStr) {
        if(listStr.indexOf("[") != -1){
            listStr = listStr.replace("[", "");
        }
        if(listStr.indexOf("]") != -1){
            listStr = listStr.replace("]", "");
        }
        SysRoom sysRoom = this.appointmentDao.selectRoom(roomId);
        String[] str = listStr.split(",");
        sysRoom.setPassengerCount(sysRoom.getPassengerCount()-str.length);
        for (int i = 0;i<str.length;i++){
            if(str[i].indexOf("\"") != -1){
                str[i] = str[i].replace("\"", "");
            }
            Integer passengerId = Integer.parseInt(str[i]);
            boolean isOk = this.appointmentDao.updatePassengerDataByRemove(passengerId)>0;
            if (isOk!=true){
                throw new RuntimeException("修改旅客信息出错");
            }
        }
        boolean isOk = this.appointmentDao.updateRoomData(sysRoom)>0;
        if (!isOk){
            throw new RuntimeException("修改房间信息出错");
        }
        return true;
    }

    @Override
    public SysRoom selectRoomByAppId(Integer appointmentId, Integer roomId) {
        return this.appointmentDao.selectRoomByAppId(appointmentId,roomId);
    }
}
