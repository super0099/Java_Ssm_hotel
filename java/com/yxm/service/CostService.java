package com.yxm.service;

import com.yxm.po.SysIncome;
import com.yxm.vo.IncomeColumnarVo;
import com.yxm.vo.LayuiTableData;

public interface CostService {
    IncomeColumnarVo selectIncomeColumnarVo(String years);
    LayuiTableData<SysIncome> selectIncomeAll(Integer page, Integer limit);
}
