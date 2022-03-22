package com.yxm.controller;

import com.yxm.po.SysUser;
import com.yxm.service.MenuService;
import com.yxm.util.ProjectParameter;
import com.yxm.vo.MenuTableTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        ModelAndView mv;
        if(user!=null){
            List<MenuTableTreeVo> list = menuService.selectMenuByPositionId(user.getPositionId());
            mv = new ModelAndView("/home");
            mv.addObject("loginUser",user);
            mv.addObject("menuList",list);
            return mv;
        }else {
            mv = new ModelAndView("redirect:/");
            return mv;
        }
    }
    @RequestMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView("/welcome");
        return mv;
    }
}
