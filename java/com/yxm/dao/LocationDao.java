package com.yxm.dao;

import com.yxm.po.SysRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationDao {
    List<SysRoom> selectCountRoom();
    List<SysRoom> selectCountRooms(Byte roomState);
    Integer updateRoomState(@Param("roomId") Integer roomId, @Param("roomState") Byte roomState);
    List<SysRoom> selectRoomByText(@Param("floor")Integer floor, @Param("roomName")String roomName);
}
