package com.yxm.controller;

import com.yxm.po.SysAppointment;
import com.yxm.po.SysEnvironment;
import com.yxm.service.PassengerService;
import com.yxm.util.JsonMsg;
import com.yxm.util.Tools;
import com.yxm.vo.PassengerDataVo;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    private static final String UPLOAD_PATH="G:/yxm/javaProjectUp/hotel/user/";
    @Autowired
    private PassengerService passengerService;

    @RequestMapping("index")
    public String index(){
        return "/passenger";
    }



    @RequestMapping(value = "/selectEnvironmentPicture",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<SysEnvironment> selectEnvironmentPicture(byte pictureType){
        return this.passengerService.selectEnvironmentPicture(pictureType);
    }

    @RequestMapping(value = "/appointment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg appointment(SysAppointment sysAppointment,String passengerData){
        JsonMsg jsonMsg = new JsonMsg();
        if(passengerData.indexOf("[") != -1){
            passengerData = passengerData.replace("[", "");
        }
        if(passengerData.indexOf("]") != -1){
            passengerData = passengerData.replace("]", "");
        }
        String[] str = passengerData.split("},");
        List<PassengerDataVo> passengerDataVos = new ArrayList<>();
        for (int i = 0;i<str.length;i++){
            String newStr = str[i]+"}";
            JSONObject obj = new JSONObject().fromObject(newStr);//将json字符串转换为json对象
            PassengerDataVo jb = (PassengerDataVo)JSONObject.toBean(obj,PassengerDataVo.class);
            if (i==0){
                sysAppointment.setPassengerName(jb.getPassengerName());
                sysAppointment.setPassengerId(jb.getPassengerIdCar());
            }
            passengerDataVos.add(jb);
        }
        if(sysAppointment.getPassengerAmount()!=str.length){
            jsonMsg.setMsg("旅客数量不对称");
            return jsonMsg;
        }
        if((sysAppointment.getPassengerAmount())*(sysAppointment.getPassengerRoom())<str.length){
            jsonMsg.setMsg("旅客数量和房间数量不匹配");
            return jsonMsg;
        }
        Random random = new Random();
        String orderNumber = String.valueOf(random.nextInt(90000000) + 10000000);
        sysAppointment.setOrderNumber(newOrderNumber(orderNumber));
        boolean isOk = this.passengerService.addAppointment(sysAppointment,passengerDataVos);
        if (isOk){
            jsonMsg.setMsg(sysAppointment.getOrderNumber());
            jsonMsg.setState(true);
        }
        return jsonMsg;
    }

    /**
     * 根据图片名返回图片 流
     */
    @RequestMapping(value = "/getPortraitImage")
    public void getPortraitImage(String imgName, HttpServletResponse response) throws IOException {
        if (Tools.isNotNull(imgName)){
            //图片名不未空
            String imgPath=UPLOAD_PATH+imgName;
            File fileImg=new File(imgPath);
            if (fileImg.exists()){
                //指定返的类型
                response.setContentType(Tools.getImageContentType(imgName));

                InputStream in=null;
                OutputStream out=null;
                try {
                    in= new FileInputStream(fileImg);
                    out=response.getOutputStream();
                    IOUtils.copy(in,out);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (in!=null)in.close();
                    if (out!=null)out.close();
                }
            }
        }
    }

    private String newOrderNumber(String orderNumber){
        boolean isExist = this.passengerService.selectOrderNumberExist(orderNumber);
        if (isExist){
            return orderNumber;
        }else {
            Random random = new Random();
            String userNames = String.valueOf(random.nextInt(90000000) + 10000000);
            return newOrderNumber(userNames);
        }
    }
}
