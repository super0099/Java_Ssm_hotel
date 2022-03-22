package com.yxm.service.impl;

import com.yxm.dao.LocationDao;
import com.yxm.po.SysRoom;
import com.yxm.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public List<SysRoom> selectCountRoom() {
        return this.locationDao.selectCountRoom();
    }

    @Override
    public List<SysRoom> selectCountRooms(Byte roomState) {
        return this.locationDao.selectCountRooms(roomState);
    }

    @Override
    public boolean updateRoomState(Integer roomId, Byte roomState) {
        return this.locationDao.updateRoomState(roomId,roomState)>0;
    }

    @Override
    public List<SysRoom> selectRoomByText(Integer floor, String roomName) {
        return this.locationDao.selectRoomByText(floor,roomName);
    }
}
