package com.yxm.controller;

import com.yxm.dao.PassengerDao;
import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;
import com.yxm.po.SysRoom;
import com.yxm.service.AppointmentService;
import com.yxm.service.PassengerService;
import com.yxm.util.JsonMsg;
import com.yxm.vo.H5SelectVo;
import com.yxm.vo.RoomPassengerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private PassengerDao passengerDao;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/inquireOrder",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg inquireOrder(String orderNumber){
        JsonMsg jsonMsg = new JsonMsg();
        SysAppointment sysAppointment = this.passengerDao.selectOrderNumber(orderNumber);
        if (sysAppointment!=null){
            jsonMsg.setData(this.appointmentService.inquireOrder(orderNumber));
            jsonMsg.setState(true);
        }else {
            jsonMsg.setMsg("该订单不存在");
        }
        return jsonMsg;
    }
    @RequestMapping(value = "/truePassenger",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg truePassenger(String PassengerId,Integer appointmentId,String PassengerName){
        JsonMsg jsonMsg = new JsonMsg();
        if (PassengerId==null){
            jsonMsg.setMsg("错误");
            return jsonMsg;
        }
        if (appointmentId==null){
            jsonMsg.setMsg("错误");
            return jsonMsg;
        }
        SysPassenger sysPassenger = this.appointmentService.truePassenger(PassengerId,appointmentId,PassengerName);
        if (sysPassenger!=null){
            boolean isOk = this.appointmentService.verificationPassenger(sysPassenger.getId());
            if (isOk){
                jsonMsg.setState(true);
            }else {
                jsonMsg.setMsg("错误");
            }
        }else {
            jsonMsg.setMsg("错误");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/selectNurseTypeBerth",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<H5SelectVo> selectNurseTypeBerth(){
        List<H5SelectVo> data = this.appointmentService.selectNurseTypeBerth();
        return data;
    }

    @RequestMapping(value = "selectRoom",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg selectRoom(Integer appointmentId,Integer roomId){
        JsonMsg jsonMsg = new JsonMsg();
        if (appointmentId==null||roomId==null){
            jsonMsg.setMsg("数据异常");
            return jsonMsg;
        }
        SysRoom sysRoom = this.appointmentService.selectRoom(roomId);
        SysRoom sysRoom1 = this.appointmentService.selectRoomByAppId(appointmentId,roomId);
        if (sysRoom1!=null){
            jsonMsg.setMsg("你已选择该房间,请重新选择");
            return jsonMsg;
        }
        if(sysRoom!=null){
            boolean isOk = this.appointmentService.updateRoom(roomId,appointmentId);
            if (isOk){
                jsonMsg.setData(sysRoom);
                jsonMsg.setState(true);
            }else {
                jsonMsg.setMsg("数据异常");
            }
        }else {
            jsonMsg.setMsg("数据异常");
        }
        return jsonMsg;
    }
    @RequestMapping(value = "/inquireRoomPersonnel",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysPassenger> inquireRoomPersonnel(Integer roomId){
        return this.appointmentService.inquireRoomPersonnel(roomId);
    }

    @RequestMapping(value = "/selectAppointmentState",produces = "application/json;charset=utf-8")
    @ResponseBody
    public SysAppointment selectAppointmentState(Integer appointmentId){
        return this.appointmentService.selectAppointmentState(appointmentId);
    }

    @RequestMapping(value = "/inquireOrderById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public SysAppointment inquireOrderById(Integer appointmentId){
        return this.appointmentService.selectAppointmentState(appointmentId);
    }
    @RequestMapping(value = "/selectRoomData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public RoomPassengerVo selectRoomData(Integer roomId, Integer appointmentId){
        RoomPassengerVo passengerVo = new RoomPassengerVo();
        SysRoom sysRoom = this.appointmentService.selectRoom(roomId);
        List<SysPassenger> passengerList = this.appointmentService.selectOrderPassenger(appointmentId);
        passengerVo.setAppointmentId(sysRoom.getAppointmentId());
        passengerVo.setPassengerCount(sysRoom.getPassengerCount());
        passengerVo.setRoomName(sysRoom.getRoomName());
        passengerVo.setRoomState(sysRoom.getRoomState());
        passengerVo.setId(sysRoom.getId());
        passengerVo.setPassengerList(passengerList);
        return passengerVo;
    }
    @RequestMapping(value = "/addRoomPassenger",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg addRoomPassenger(Integer roomId,String listStr){
        JsonMsg jsonMsg = new JsonMsg();
        boolean isOK = this.appointmentService.addRoomPassenger(roomId,listStr);
        if (isOK){
            jsonMsg.setState(true);
        }
        return jsonMsg;
    }
    @RequestMapping(value = "/selectRoomDataBySubtract",produces = "application/json;charset=utf-8")
    @ResponseBody
    public RoomPassengerVo selectRoomDataBySubtract(Integer roomId, Integer appointmentId){
        RoomPassengerVo passengerVo = new RoomPassengerVo();
        SysRoom sysRoom = this.appointmentService.selectRoom(roomId);
        List<SysPassenger> passengerList = this.appointmentService.selectRoomDataBySubtract(roomId,appointmentId);
        passengerVo.setAppointmentId(sysRoom.getAppointmentId());
        passengerVo.setPassengerCount(sysRoom.getPassengerCount());
        passengerVo.setRoomName(sysRoom.getRoomName());
        passengerVo.setRoomState(sysRoom.getRoomState());
        passengerVo.setId(sysRoom.getId());
        passengerVo.setPassengerList(passengerList);
        return passengerVo;
    }

    @RequestMapping(value = "/removeRoomPassenger",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg removeRoomPassenger(Integer roomId,String listStr){
        JsonMsg jsonMsg = new JsonMsg();
        boolean isOK = this.appointmentService.removeRoomPassenger(roomId,listStr);
        if (isOK){
            jsonMsg.setState(true);
        }
        return jsonMsg;
    }
}
