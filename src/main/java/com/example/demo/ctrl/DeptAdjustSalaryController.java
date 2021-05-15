package com.example.demo.ctrl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.DeptAdjustSalary;
import com.example.demo.service.IDeptAdjustSalaryService;
import com.example.demo.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("deptAdjustSalary")
public class DeptAdjustSalaryController {

    @Autowired
    private IDeptAdjustSalaryService deptAdjustSalaryService;

    /**
     * 查询历史数据员工工号
     */
    @RequestMapping("/selectAdjustSalaryHistoryByUserId")
    @ResponseBody
    public String selectAdjustSalaryHistoryByUserId(String userId){
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(userId)){
            result.put("adjustSalaryHistory",deptAdjustSalaryService.selectAdjustSalaryHistoryByUserId(userId));
            result.put("status",0);
        }else {
            result.put("status",-1);
            result.put("msg","数据查询失败");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 保存数据
     * @param deptAdjustSalaries
     * @return
     */
    @RequestMapping("/saveDeptAdjustSalary")
    public String saveDeptAdjustSalary(@RequestBody List<DeptAdjustSalary> deptAdjustSalaries){
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(deptAdjustSalaries)){
            deptAdjustSalaryService.saveDeptAdjustSalary(deptAdjustSalaries);
            result.put("adjustSalary","");
            result.put("status","0");
        }else {
            result.put("status","-1");
            result.put("msg","保存数据失败");
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/deleteDeptAdjustSalary")
    @ResponseBody
    public Map deleteDeptAdjustSalary(Long adjustSalaryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("status", "0");
//            map.put("deptAdjustSalaryApply", deptAdjustSalaryService.deleteDeptAdjustSalary(adjustSalaryId));
        } catch (Exception e) {
            map.put("status", "-1");
            map.put("msg", ExceptionUtil.getExceptionAllInformation(e));
        }
        return map;
    }

}
