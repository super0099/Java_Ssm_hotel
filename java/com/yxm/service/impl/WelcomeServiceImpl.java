package com.yxm.service.impl;

import com.yxm.dao.WelcomeDao;
import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;
import com.yxm.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WelcomeServiceImpl implements WelcomeService {
    @Autowired
    private WelcomeDao welcomeDao;
    @Override
    public List<SysAppointment> selectWelcomeData() {
        return this.welcomeDao.selectWelcomeData();
    }

    @Override
    public List<SysPassenger> selectWelcomeDataByP() {
        return this.welcomeDao.selectWelcomeDataByP();
    }

    @Override
    public Integer passengerCode() {
        return this.welcomeDao.passengerCode();
    }

    @Override
    public Integer userNameCode() {
        return this.welcomeDao.userNameCode();
    }

    @Override
    public Integer roomCode() {
        return this.welcomeDao.roomCode();
    }

    @Override
    public Integer orderCode() {
        return this.welcomeDao.orderCode();
    }
}
