package com.yxm.controller;

import com.yxm.po.SysIncome;
import com.yxm.service.CostService;
import com.yxm.vo.IncomeColumnarVo;
import com.yxm.vo.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/cost")
@Controller
public class CostController {
    @Autowired
    private CostService costService;

    @RequestMapping(value = "/selectIncomeColumnarVo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public IncomeColumnarVo selectIncomeColumnarVo(String years){
        IncomeColumnarVo incomeColumnarVo = this.costService.selectIncomeColumnarVo(years);
        return incomeColumnarVo;
    }
    @RequestMapping(value = "/selectIncomeAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<SysIncome> selectIncomeAll(Integer page, Integer limit){
        return this.costService.selectIncomeAll(page,limit);
    }
}
