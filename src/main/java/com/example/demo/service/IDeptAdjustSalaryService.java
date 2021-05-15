package com.example.demo.service;

import com.example.demo.entity.DeptAdjustSalary;

import java.util.List;

public interface IDeptAdjustSalaryService {
    /**
     * 查询历史数据员工工号
     * @param userId
     * @return
     */
    List selectAdjustSalaryHistoryByUserId(String userId);

    /**
     * 保存数据
     * @param deptAdjustSalaries
     * @return
     */
    List<DeptAdjustSalary> saveDeptAdjustSalary(List<DeptAdjustSalary> deptAdjustSalaries);
}
