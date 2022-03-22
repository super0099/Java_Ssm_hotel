package com.yxm.controller;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysPassenger;
import com.yxm.service.WelcomeService;
import com.yxm.vo.WelcomeCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @Autowired
    private WelcomeService welcomeService;
    @RequestMapping(value = "/selectWelcomeData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysAppointment> selectWelcomeData(){
        return this.welcomeService.selectWelcomeData();
    }


    @RequestMapping(value = "/selectWelcomeDataByP",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysPassenger> selectWelcomeDataByP(){
        return this.welcomeService.selectWelcomeDataByP();
    }

    @RequestMapping(value = "/selectWelcomeDataByCode",produces = "application/json;charset=utf-8")
    @ResponseBody
    public WelcomeCodeVo selectWelcomeDataByCode(){
        WelcomeCodeVo welcomeCodeVo = new WelcomeCodeVo();
        Integer passengerCode = this.welcomeService.passengerCode();
        Integer userNameCode = this.welcomeService.userNameCode();
        Integer roomCode = this.welcomeService.roomCode();
        Integer orderCode = this.welcomeService.orderCode();
        welcomeCodeVo.setPassengerCode(passengerCode);
        welcomeCodeVo.setUserNameCode(userNameCode);
        welcomeCodeVo.setRoomCode(roomCode);
        welcomeCodeVo.setOrderCode(orderCode);
        return welcomeCodeVo;
    }
}
