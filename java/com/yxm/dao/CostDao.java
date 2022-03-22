package com.yxm.dao;

import com.yxm.po.SysIncome;
import com.yxm.vo.SysMonthVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostDao {
    SysMonthVo selectMonthVo(String years);
    Integer selectIncomeCount();
    List<SysIncome> selectIncomeAll(@Param("page")Integer page, @Param("limit") Integer limit);
}
