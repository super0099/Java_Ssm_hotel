package com.yxm.service;

import com.yxm.po.SysUser;

public interface LoginService {
    SysUser selectUserName(String userName);
}
