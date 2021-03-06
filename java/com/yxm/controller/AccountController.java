package com.yxm.controller;

import com.yxm.po.SysUser;
import com.yxm.service.AccountService;
import com.yxm.util.JsonMsg;
import com.yxm.util.MD5Util;
import com.yxm.util.Tools;
import com.yxm.vo.H5SelectVo;
import com.yxm.vo.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RequestMapping(value = "/system")
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    private static final String UPLOAD_PATH="G:/yxm/javaProjectUp/hotel/user/";

    @RequestMapping("/account")
    public String account(){
        return "/account";
    }

    @RequestMapping("/jurisdiction")
    public String jurisdiction(){
        return "/jurisdiction";
    }

    @RequestMapping(value = "/selectUserAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<SysUser> selectUserAll(Integer page, Integer limit){
        return this.accountService.selectUserAll(page,limit);
    }

    @RequestMapping(value = "/selectPositionForH5Select",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<H5SelectVo> selectPositionForH5Select(){
        return this.accountService.selectPositionForH5Select();
    }

    @RequestMapping(value = "/selectUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg selectUserById(Integer userId){
        JsonMsg jsonMsg = new JsonMsg();
        if (userId!=null){
            jsonMsg.setState(true);
            jsonMsg.setData(this.accountService.selectUserById(userId));
        }else {
            jsonMsg.setMsg("????????????");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/deleteElderById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg deleteElderById(Integer userId){
        JsonMsg jsonMsg = new JsonMsg();
        if(userId!=null){
            if(userId!=1){
                //????????????????????????
                SysUser sysUser = this.accountService.selectUserById(userId);
                boolean isOk = this.accountService.deleteElderById(userId);
                if (isOk){
                    if (sysUser.getUserPortrait()!=null){
                        //?????????????????????
                        String oldPath = UPLOAD_PATH+sysUser.getUserPortrait();
                        File oldImg = new File(oldPath);
                        if (oldImg.exists()){
                            oldImg.delete();
                        }
                    }
                    jsonMsg.setMsg("????????????");
                    jsonMsg.setState(true);
                }else {
                    jsonMsg.setMsg("????????????");
                }
            }else {
                jsonMsg.setMsg("???????????????????????????");
            }
        }else {
            jsonMsg.setMsg("????????????");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/insert",produces = "application/json;cahrset=utf-8")
    @ResponseBody
    public JsonMsg insert(SysUser sysUser, MultipartFile portraitFile){
        JsonMsg jsonMsg = new JsonMsg();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");

        //????????????????????????????????????
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String filePaths = "";
        if (portraitFile!=null){
            //???????????????  item.getName()--????????????
            String fileName = dateFormat.format(new Date()) + System.nanoTime() + Tools.getFileExt(portraitFile.getOriginalFilename());
            //????????????
            filePaths = UPLOAD_PATH + fileName;
            //?????????????????????????????????????????????
            sysUser.setUserPortrait(fileName);
        }else {
            jsonMsg.setMsg("??????????????????");
            return jsonMsg;
        }

        if (sysUser.getUserName()==null){
            jsonMsg.setMsg("?????????????????????");
            return jsonMsg;
        }
        if(sysUser.getUserPassword()!=null){
            Random random = new Random();
            //?????????????????????8???????????????   10000000 ~ 99999999
            String salt = String.valueOf(random.nextInt(90000000) + 10000000);
            //??????????????????+??? ???MD5???
            String userPassword = MD5Util.getMD5(sysUser.getUserPassword() + salt);
            sysUser.setUserPassword(userPassword);
            sysUser.setPasswordSalt(salt);
        }else {
            jsonMsg.setMsg("??????????????????");
            return jsonMsg;
        }
        if (sysUser.getPositionId()==null){
            jsonMsg.setMsg("???????????????");
            return jsonMsg;
        }
        if (sysUser.getUserState()==null){
            jsonMsg.setMsg("?????????????????????");
            return jsonMsg;
        }
        if(sysUser.getRealName()==null){
            jsonMsg.setMsg("?????????????????????");
            return jsonMsg;
        }
        try {
            boolean isOk = this.accountService.insert(sysUser);
            if(isOk){
                File saveFile = new File(filePaths);
                //?????????????????????
                portraitFile.transferTo(saveFile);
                jsonMsg.setMsg("????????????");
                jsonMsg.setState(true);
            }
        }catch (RuntimeException | IOException e){
            jsonMsg.setMsg("????????????");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/update",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg update(SysUser sysUser,MultipartFile portraitFile) throws IOException {
        JsonMsg jsonMsg = new JsonMsg();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
        if(sysUser.getUserName()!=null){
            if (sysUser.getId()==1&&sysUser.getPositionId()!=1){
                jsonMsg.setMsg("???????????????????????????");
                return jsonMsg;
            }
            //????????????????????????????????????
            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileName ="";
            if(portraitFile!=null&&portraitFile.getBytes().length>0){
                //?????????????????????
                fileName = dateFormat.format(new Date())+System.nanoTime()+Tools.getFileExt(portraitFile.getOriginalFilename());
                //?????????????????????????????????????????????
                sysUser.setUserPortrait(fileName);
            }
            SysUser sysUser1 = this.accountService.selectUserById(sysUser.getId());
            try{
                boolean isOk=this.accountService.update(sysUser);
                if (isOk){
                    if(portraitFile!=null&&portraitFile.getBytes().length>0){
                        File ImgPath = new File(UPLOAD_PATH+fileName);
                        //?????????????????????
                        portraitFile.transferTo(ImgPath);
                        //???????????????
                        //?????????????????????
                        String oldPath = UPLOAD_PATH+sysUser1.getUserPortrait();
                        //
                        File oldImg = new File(oldPath);
                        if (oldImg.exists()){
                            oldImg.delete();
                        }
                    }
                    jsonMsg.setState(true);
                    jsonMsg.setMsg("????????????");
                }else{
                    jsonMsg.setMsg("????????????");
                }
            }catch (RuntimeException e){
                jsonMsg.setMsg("????????????");
            }
        }else {
            jsonMsg.setMsg("?????????????????????");
            return jsonMsg;
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/delete",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg delete(Integer userId){
        JsonMsg jsonMsg = new JsonMsg();
        if (userId==1){
            jsonMsg.setMsg("????????????????????????");
            return jsonMsg;
        }
        boolean isOk = this.accountService.delete(userId);
        if (isOk){
            jsonMsg.setMsg("????????????");
            jsonMsg.setState(true);
        }else {
            jsonMsg.setMsg("????????????");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/resetPassword",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg resetPassword(Integer userId,String password){
        JsonMsg jsonMsg=new JsonMsg();
        if (userId!=null&&userId>0){
            if (Tools.isNotNull(password)){
                //????????????????????????
                SysUser oldUser=this.accountService.selectUserById(userId);
                Random random = new Random();
                //?????????????????????8???????????????   10000000 ~ 99999999
                String salt = String.valueOf(random.nextInt(90000000) + 10000000);
                //??????????????????+??? ???MD5???
                String userPassword = MD5Util.getMD5(password + salt);
                oldUser.setUserPassword(userPassword);
                oldUser.setPasswordSalt(salt);
                //??????????????????
                try{
                    boolean isOk= this.accountService.updates(oldUser);
                    if (isOk){
                        jsonMsg.setState(true);
                        jsonMsg.setMsg("????????????");
                    }else {
                        jsonMsg.setMsg("????????????");
                    }
                }catch (RuntimeException e){
                    jsonMsg.setMsg("????????????");
                }
            }else {
                jsonMsg.setMsg("??????????????????");
            }
        }else {
            jsonMsg.setMsg("????????????");
        }
        return jsonMsg;
    }
}
