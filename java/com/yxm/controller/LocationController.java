package com.yxm.controller;

import com.yxm.po.SysPassenger;
import com.yxm.po.SysRoom;
import com.yxm.service.AppointmentService;
import com.yxm.service.LocationService;
import com.yxm.util.JsonMsg;
import com.yxm.vo.RoomPassengerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/location")
@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/selectCountRoom",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysRoom> selectCountRoom(){
        return this.locationService.selectCountRoom();
    }
    @RequestMapping(value = "/selectCountRooms",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysRoom> selectCountRooms(Byte roomState){
        return this.locationService.selectCountRooms(roomState);
    }

    @RequestMapping(value = "/selectRoomData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public RoomPassengerVo selectRoomData(Integer roomId){
        RoomPassengerVo roomPassengerVo = new RoomPassengerVo();
        SysRoom sysRoom = this.appointmentService.selectRoom(roomId);
        List<SysPassenger> passengerList = this.appointmentService.inquireRoomPersonnel(roomId);
        roomPassengerVo.setPassengerList(passengerList);
        roomPassengerVo.setRoomState(sysRoom.getRoomState());
        roomPassengerVo.setRoomName(sysRoom.getRoomName());
        roomPassengerVo.setPassengerCount(sysRoom.getPassengerCount());
        roomPassengerVo.setId(sysRoom.getId());
        roomPassengerVo.setAppointmentId(sysRoom.getAppointmentId());
        roomPassengerVo.setRoomFloor(sysRoom.getRoomFloor());
        return roomPassengerVo;
    }

    @RequestMapping(value = "/updateRoomState",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg updateRoomState(Integer roomId,Byte roomState){
        JsonMsg jsonMsg = new JsonMsg();
        if (roomId==null||roomId==0){
            jsonMsg.setMsg("数据异常");
            return jsonMsg;
        }
        if (roomState==null||roomState==0){
            jsonMsg.setMsg("数据异常");
            return jsonMsg;
        }
        boolean isOk = this.locationService.updateRoomState(roomId,roomState);
        if (isOk){
            jsonMsg.setState(true);
        }else {
            jsonMsg.setMsg("修改失败");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/selectRoomByText",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysRoom> selectRoomByText(Integer floor,String roomName){
        return this.locationService.selectRoomByText(floor,roomName);
    }
}
