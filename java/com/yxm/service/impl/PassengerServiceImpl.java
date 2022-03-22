package com.yxm.service.impl;

import com.yxm.dao.PassengerDao;
import com.yxm.po.SysAppointment;
import com.yxm.po.SysEnvironment;
import com.yxm.po.SysPassenger;
import com.yxm.service.PassengerService;
import com.yxm.vo.PassengerDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerDao passengerDao;

    @Override
    public List<SysEnvironment> selectEnvironmentPicture(byte pictureType) {
        return this.passengerDao.selectEnvironmentPicture(pictureType);
    }

    @Override
    public boolean addAppointment(SysAppointment sysAppointment, List<PassengerDataVo> passengerDataVo) {
        sysAppointment.setPresentState((byte) 1);

        boolean isOk = this.passengerDao.addAppointment(sysAppointment)>0;
        if (isOk){
            for (PassengerDataVo obj:passengerDataVo) {
                SysPassenger sysPassenger = new SysPassenger();
                sysPassenger.setPassengerId(obj.getPassengerIdCar());
                sysPassenger.setPassengerName(obj.getPassengerName());
                sysPassenger.setAppointmentId(sysAppointment.getId());
                sysPassenger.setVerification((byte) 1);
                boolean isTrue = this.passengerDao.addAppointmentRoom(sysPassenger)>0;
                if (isTrue!=true){
                    throw new RuntimeException("新增旅客信息出错");
                }
            }
        }else {
            throw new RuntimeException("新增预订信息出错");
        }
        return true;
    }

    @Override
    public boolean selectOrderNumberExist(String orderNumber) {
        return this.passengerDao.selectOrderNumber(orderNumber)==null;
    }
}
