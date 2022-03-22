package com.yxm.service;

import com.yxm.po.SysRoom;

import java.util.List;

public interface LocationService {
    List<SysRoom> selectCountRoom();
    List<SysRoom> selectCountRooms(Byte roomState);
    boolean updateRoomState(Integer roomId,Byte roomState);
    List<SysRoom> selectRoomByText(Integer floor,String roomName);
}
