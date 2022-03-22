package com.yxm.controller;

import com.yxm.po.SysUser;
import com.yxm.service.LoginService;
import com.yxm.util.JsonMsg;
import com.yxm.util.MD5Util;
import com.yxm.util.ProjectParameter;
import com.yxm.util.ValidateImage.PngCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
@RequestMapping("/login")
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/imgCode")
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置返回类型
        response.setContentType("image/png");
        PngCaptcha captcha = new PngCaptcha(135,50,5);
        OutputStream out = response.getOutputStream();
        String identityKay = captcha.out(out);
        HttpSession session = request.getSession();
        session.setAttribute(ProjectParameter.SESSION_LOGIN_IDENTITY,identityKay);
        out.flush();
        out.close();
    }
    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg login(HttpServletRequest request,String userName,String userPassword,String verification){
        JsonMsg jsonMsg = new JsonMsg();
        HttpSession session = request.getSession();
        String authCode = (String) session.getAttribute(ProjectParameter.SESSION_LOGIN_IDENTITY);
        if (userName==null||userName==""){
            jsonMsg.setMsg("用户名不能为空");
            return jsonMsg;
        }
        if (userPassword==null||userPassword==""){
            jsonMsg.setMsg("密码不能为空");
            return jsonMsg;
        }
        if (verification==null||verification==""){
            jsonMsg.setMsg("验证码不能为空");
            return jsonMsg;
        }
        if (authCode.equalsIgnoreCase(verification)){
            SysUser sysUser = this.loginService.selectUserName(userName);
            if (sysUser!=null){
                String password = MD5Util.getMD5(userPassword+sysUser.getPasswordSalt());
                if(sysUser.getUserPassword().equals(password)){
                    session.setAttribute(ProjectParameter.SESSION_USER,sysUser);
                    jsonMsg.setState(true);
                }else {
                    jsonMsg.setMsg("密码错误");
                }
            }else {
                jsonMsg.setMsg("该用户不存在");
            }
        }else {
            jsonMsg.setMsg("验证码不正确");
        }
        return jsonMsg;
    }
    @RequestMapping(value = "/loginOut",produces = "application/json;charset=utf-8")
    @ResponseBody
    public boolean loginOut(HttpSession session) {
        session.removeAttribute(ProjectParameter.SESSION_USER);
        return true;
    }
}
