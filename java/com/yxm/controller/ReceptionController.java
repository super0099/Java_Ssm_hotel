package com.yxm.controller;

import com.yxm.po.SysUser;
import com.yxm.util.ProjectParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reception")
public class ReceptionController {

    @RequestMapping("/appointment")
    public ModelAndView appointment(Integer code, HttpSession session){
        if (code==null){
            SysUser sysUser = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
            ModelAndView mv = new ModelAndView("/appointment");
            code=0;
            mv.addObject("code",code);
            mv.addObject("loginUser",sysUser);
            return mv;
        }
        if (code==1){
            ModelAndView mv = new ModelAndView("/appointment");
            mv.addObject("code",code);
            return mv;
        }
        return null;
    }
    @RequestMapping("/location")
    public String location(){
        return "/location";
    }
    @RequestMapping("/abdicate")
    public String abdicate(){
        return "/abdicate";
    }
    @RequestMapping("/income")
    public String income(){
        return "/income";
    }
}
